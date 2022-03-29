package com.training.library.converter.impl;

import com.training.library.converter.UserConverter;
import com.training.library.dto.UserDto;
import com.training.library.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {
    private final PasswordEncoder encoder;

    public UserConverterImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User fromDto(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setPassword(encoder.encode(userDto.getPassword()));

        return user;
    }
}
