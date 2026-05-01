package com.yozakura_minato.kensaku_be.service.implement;

import com.yozakura_minato.kensaku_be.dto.JwtInformation;
import com.yozakura_minato.kensaku_be.dto.TokenPayload;
import com.yozakura_minato.kensaku_be.dto.request.SignInRequestDto;
import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignInResponseDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;
import com.yozakura_minato.kensaku_be.entity.RedisToken;
import com.yozakura_minato.kensaku_be.entity.Users;
import com.yozakura_minato.kensaku_be.mapper.UserMapper;
import com.yozakura_minato.kensaku_be.repository.RedisTokenRepository;
import com.yozakura_minato.kensaku_be.repository.UserRepository;
import com.yozakura_minato.kensaku_be.service.JwtService;
import com.yozakura_minato.kensaku_be.service.UserService;
import com.yozakura_minato.kensaku_be.exception.message.SignUpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Slf4j
@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTokenRepository redisTokenRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public SignUpResponseDto signUp(SignUpRequestDto signUpReq) {

        // Check for exists email
        Users existEmail = userRepository.findByEmail(signUpReq.getEmail());
        if (existEmail != null) {
            throw new RuntimeException(SignUpException.Email.exits);
        }
        Users newUser = userMapper.signUpReqToEntity(signUpReq);

        // Hash password
        String password = signUpReq.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setHashedPassword(hashedPassword);

        // Save user
        userRepository.save(newUser);
        return userMapper.signUpEntityToRes(newUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SignInResponseDto signIn(SignInRequestDto signInReq) {
        // Check for (username, hashedPassword)
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                signInReq.getEmail(),
                signInReq.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Users user = (Users) authentication.getPrincipal();
        assert user != null;

        // Generate (access, refresh) JWT
        TokenPayload accessTokenPayload = jwtService.generateAccessToken(user);
        TokenPayload refreshTokenPayload = jwtService.generateRefreshToken(user);

        RedisToken redisToken = RedisToken.builder()
                .jwtId(refreshTokenPayload.getJwtId())
                .expirationTime(refreshTokenPayload.getExpirationTime().getTime())
                .build();

        redisTokenRepository.save(redisToken);

        return SignInResponseDto
                .builder()
                .accessToken(accessTokenPayload.getToken())
                .refreshToken(refreshTokenPayload.getToken())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signOut(String token) throws ParseException {
        JwtInformation jwtInformation = jwtService.parseToken(token);
        String jwtId = jwtInformation.getJwtId();
        Date issueTime = jwtInformation.getIssueTime();
        Date expirationTime = jwtInformation.getExpirationTime();

        if(expirationTime.before(new Date())) {
            return;
        }
        RedisToken redisToken = RedisToken.builder()
                .jwtId(jwtId)
                .expirationTime(expirationTime.getTime() - issueTime.getTime())
                .build();

        redisTokenRepository.save(redisToken);
        log.info("Signed out successfully!");
    }

}
