package com.yozakura_minato.kensaku_be.service.impl;

import com.yozakura_minato.kensaku_be.dto.UserRequestDto;
import com.yozakura_minato.kensaku_be.dto.UserResponseDto;
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

    public UserResponseDto signUp(UserRequestDto userReqDto) {
        // Check email
        User existEmail = userRepository.findByEmail(userReqDto.getEmail());
        if (existEmail != null) {
            throw new RuntimeException("existsEmail.validaion.exception");
        }
        User newUser = userMapper.reqDtoToEntity(userReqDto);
        // Hash password
        String hashedPassword = passwordEncoder.encode(userReqDto.getPassword());
        newUser.setHashedPassword(hashedPassword);
        // Record time
        newUser.setCreatedDateTime(LocalDateTime.now());
        newUser.setDeleted(false);
        userRepository.save(newUser);
        return userMapper.entityToResDto(newUser);
    }
}
