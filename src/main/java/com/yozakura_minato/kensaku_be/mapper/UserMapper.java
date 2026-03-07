package com.yozakura_minato.kensaku_be.mapper;

import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;
import com.yozakura_minato.kensaku_be.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // ============ FOR SIGN UP ============ //
    /**
     * @param signUpReq (SignUpRequestDto)
     * @return (Users)
     */
    Users signUpReqToEntity(SignUpRequestDto signUpReq);

    /**
     * @param user (Users)
     * @return (SignUpResponseDto)
     */
    SignUpResponseDto signUpEntityToRes(Users user);

}
