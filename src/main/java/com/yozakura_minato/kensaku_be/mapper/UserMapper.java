package com.yozakura_minato.kensaku_be.mapper;

import com.yozakura_minato.kensaku_be.dto.UserRequestDto;
import com.yozakura_minato.kensaku_be.dto.UserResponseDto;
import com.yozakura_minato.kensaku_be.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Map struct object for users
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Method to convert from User request DTO to User entity
     * @param userRequestDto User request DTO
     * @return User response DTO
     */
    User reqDtoToEntity(UserRequestDto userRequestDto);

    /**
     * Method to convert from User entity to User response DTO
     * @param user User entity
     * @return User response DTO
     */
    UserResponseDto entityToResDto(User user);

    /**
     * Method to update User entity data from User request DTO
     * @param userRequestDto User request DTO
     * @param user User entity
     * @return void
     */
    User updateDtoToEntity(UserRequestDto userRequestDto, @MappingTarget User user);

}
