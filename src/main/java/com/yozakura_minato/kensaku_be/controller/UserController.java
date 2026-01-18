package com.yozakura_minato.kensaku_be.controller;

import com.yozakura_minato.kensaku_be.dto.UserRequestDto;
import com.yozakura_minato.kensaku_be.dto.UserResponseDto;
import com.yozakura_minato.kensaku_be.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/sign-up")
    UserResponseDto signUp(@RequestBody @Valid UserRequestDto userReqDto) {
        return userService.signUp(userReqDto);
    }

}
