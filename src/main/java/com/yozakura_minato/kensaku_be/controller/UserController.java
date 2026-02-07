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
        String password = signUpReq.getPassword();
        int typeNumber = 0;
        typeNumber += password.matches(".*[A-Z].*") ? 1 : 0;
        typeNumber += password.matches(".*[a-z].*") ? 1 : 0;
        typeNumber += password.matches(".*[0-9].*") ? 1 : 0;
        typeNumber += password.matches(".*[^A-Za-z0-9].*") ? 1 : 0;

        // Password must include at least 2 type of character
        if (typeNumber < 2) {
            throw new RuntimeException("PASSWORD_FORMAT.SIGN_UP.EXCEPTION");
        }
        return userService.signUp(signUpReq);
    }

}
