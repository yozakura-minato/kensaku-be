package com.yozakura_minato.kensaku_be.service;

import com.yozakura_minato.kensaku_be.dto.UserRequestDto;
import com.yozakura_minato.kensaku_be.dto.UserResponseDto;

public interface UserService {
    UserResponseDto signUp(UserRequestDto userReqDto);
}
