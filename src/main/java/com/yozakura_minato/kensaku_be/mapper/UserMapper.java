package com.yozakura_minato.kensaku_be.mapper;

import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;
import com.yozakura_minato.kensaku_be.dto.response.SignUpResponseDto;
import com.yozakura_minato.kensaku_be.entity.User;
import org.mapstruct.Mapper;

/**
 * Map struct object for users
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    // ============ SIGN UP MAPPERS ============ //
    /**
     * Method to convert from Sign up request DTO to User entity
     * @param signUpReq Sign up request DTO
     * @return User Entity
     */
    User signUpReqToEntity(SignUpRequestDto signUpReq);

    /**
     * Method to convert from User entity to Sign up response DTO
     * @param user User entity
     * @return Sign up response DTO
     */
    SignUpResponseDto signUpEntityToRes(User user);

}
