package com.yozakura_minato.kensaku_be.service.impl;

import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;
import com.yozakura_minato.kensaku_be.entity.User;
import com.yozakura_minato.kensaku_be.mapper.UserMapper;
import com.yozakura_minato.kensaku_be.repository.UserRepository;
import com.yozakura_minato.kensaku_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    public SignUpResponseDto signUp(SignUpRequestDto signUpReq) {

        // Check password format
        String password = signUpReq.getPassword();
        int typeNumber = 0;
        typeNumber += password.matches(".*[A-Z].*") ? 1 : 0;
        typeNumber += password.matches(".*[a-z].*") ? 1 : 0;
        typeNumber += password.matches(".*[0-9].*") ? 1 : 0;
        typeNumber += password.matches(".*[^A-Za-z0-9].*") ? 1 : 0;
        if (typeNumber < 2) {
            throw new RuntimeException("PASSWORD_FORMAT.SIGN_UP.EXCEPTION");
        }

        // Check exists email
        User existEmail = userRepository.findByEmail(signUpReq.getEmail());
        if (existEmail != null) {
            throw new RuntimeException("EMAIL_EXISTS.SIGN_UP.EXCEPTION");
        }
        User newUser = userMapper.signUpReqToEntity(signUpReq);

        // Hash password
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setHashedPassword(hashedPassword);

        // Record time
        newUser.setCreatedDateTime(LocalDateTime.now());
        newUser.setDeleted(false);

        userRepository.save(newUser);
        return userMapper.signUpEntityToRes(newUser);
    }

}
