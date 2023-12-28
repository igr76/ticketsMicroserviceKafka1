package com.example.stmlabs.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto userDto);

    UserDto toDTO(User userEntity);
}
