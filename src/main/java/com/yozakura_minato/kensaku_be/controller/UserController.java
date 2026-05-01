package com.yozakura_minato.kensaku_be.controller;

import com.yozakura_minato.kensaku_be.dto.request.SignInRequestDto;
import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignInResponseDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;
import com.yozakura_minato.kensaku_be.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
        return userService.signUp(signUpReq);
    }

    /**
     * @param signInReq (SignInRequestDto)
     * @return (SignInResponseDto)
     */
    @PostMapping("/sign-in")
    SignInResponseDto signIn(@RequestBody @Valid SignInRequestDto signInReq) {
        return userService.signIn(signInReq);
    }

    @GetMapping("auth-test")
    String authTest() {
        return "Hello World!";
    }

    @PostMapping("/sign-out")
    void signOut(@RequestHeader("Authorization") String authenticationHeader) throws ParseException {
        String token = authenticationHeader.replace("Bearer", "");
        userService.signOut(token);
    }

}
