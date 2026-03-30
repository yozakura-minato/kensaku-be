package com.yozakura_minato.kensaku_be.service;

import com.yozakura_minato.kensaku_be.dto.request.SignInRequestDto;
import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignInResponseDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;
import com.yozakura_minato.kensaku_be.exception.message.SignUpException;

public interface UserService {

    /**
     * @param signUpReq (SignUpRequestDto)
     * @return (SignUpResponseDto)
     * @throws SignUpException email exits
     */
    SignUpResponseDto signUp(SignUpRequestDto signUpReq);

    /**
     * @param signInReq (SignInRequestDto)
     * @return (SignInResponseDto)
     * @throws AssertionError
     */
    SignInResponseDto signIn(SignInRequestDto signInReq);

}
