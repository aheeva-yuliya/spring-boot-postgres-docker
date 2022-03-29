package com.training.library.service;

import com.training.library.dto.UserDto;
import com.training.library.model.User;

import java.util.Optional;

public interface UserService {
    User save(UserDto userDto);

    User getById(Long id);

    Optional<User> getByName(String name);
}
