package com.yozakura_minato.kensaku_be.mapper;

import com.yozakura_minato.kensaku_be.dto.UserRequestDto;
import com.yozakura_minato.kensaku_be.dto.UserResponseDto;
import com.yozakura_minato.kensaku_be.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User reqDtoToEntity(UserRequestDto userRequestDto);
    UserResponseDto entityToResDto(User user);
    User updateDtoToEntity(UserRequestDto userRequestDto, @MappingTarget User user);
}
