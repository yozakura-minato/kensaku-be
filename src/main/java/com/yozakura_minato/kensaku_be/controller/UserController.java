package com.yozakura_minato.kensaku_be.controller;

import com.yozakura_minato.kensaku_be.dto.request.SignInRequestDto;
import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignInResponseDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;
import com.yozakura_minato.kensaku_be.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param signUpReq (SignUpResponseDto)
     * @return (SignUpResponseDto)
     */
    @PostMapping("sign-up")
    SignUpResponseDto signUp(@RequestBody @Valid SignUpRequestDto signUpReq) {
        signUpReq.normalize();
        return userService.signUp(signUpReq);
    }

    /**
     * @param signInReq (SignInRequestDto)
     * @return (SignInResponseDto)
     */
    @PostMapping("/sign-in")
    SignInResponseDto signIn(@RequestBody @Valid SignInRequestDto signInReq) {
        signInReq.normalize();
        return userService.signIn(signInReq);
    }

    @GetMapping("auth-test")
    String authTest() {
        return "Hello World!";
    }

}
