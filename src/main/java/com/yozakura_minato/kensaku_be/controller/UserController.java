package com.yozakura_minato.kensaku_be.controller;

import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;
import com.yozakura_minato.kensaku_be.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controllers for users
 */
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Controller to handle sign up
     * @param signUpReq Sign up request DTO
     * @return Sign up response DTO
     */
    @PostMapping("/auth/sign-up")
    SignUpResponseDto signUp(@RequestBody @Valid SignUpRequestDto signUpReq) {
        return userService.signUp(signUpReq);
    }

}
