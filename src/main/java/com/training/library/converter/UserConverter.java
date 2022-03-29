package com.training.library.converter;


import com.training.library.dto.UserDto;

import com.training.library.model.User;

public interface UserConverter {
    User fromDto(UserDto userDto);
}
