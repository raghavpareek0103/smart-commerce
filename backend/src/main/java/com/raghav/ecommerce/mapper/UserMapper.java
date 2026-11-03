package com.raghav.ecommerce.mapper;

import com.raghav.ecommerce.dto.UserDto;
import com.raghav.ecommerce.model.User;

public class UserMapper {

    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
