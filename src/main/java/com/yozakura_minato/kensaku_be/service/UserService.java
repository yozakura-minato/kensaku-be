package com.yozakura_minato.kensaku_be.service;

import com.yozakura_minato.kensaku_be.dto.UserRequestDto;
import com.yozakura_minato.kensaku_be.dto.UserResponseDto;

/**
 * Services for users
 */
public interface UserService {

    /**
     * Service to handle sign up
     * @param userReqDto User request DTO
     * @return User response DTO
     */
    UserResponseDto signUp(UserRequestDto userReqDto);

}
