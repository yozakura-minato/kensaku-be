package com.yozakura_minato.kensaku_be.service;

import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;

/**
 * Services for users
 */
public interface UserService {

    /**
     * Service to handle sign up
     * @param signUpReq Sign up request DTO
     * @return Sign up response DTO
     */
    SignUpResponseDto signUp(SignUpRequestDto signUpReq);

}
