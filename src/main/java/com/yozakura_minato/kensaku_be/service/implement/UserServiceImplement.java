package com.yozakura_minato.kensaku_be.service.implement;

import com.yozakura_minato.kensaku_be.dto.request.SignInRequestDto;
import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignInResponseDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;
import com.yozakura_minato.kensaku_be.entity.Users;
import com.yozakura_minato.kensaku_be.mapper.UserMapper;
import com.yozakura_minato.kensaku_be.repository.UserRepository;
import com.yozakura_minato.kensaku_be.service.JwtService;
import com.yozakura_minato.kensaku_be.service.UserService;
import com.yozakura_minato.kensaku_be.util.message.SignUpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

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
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return SignInResponseDto
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
