package com.training.library.service.impl;

import com.training.library.converter.UserConverter;
import com.training.library.dto.UserDto;
import com.training.library.model.User;
import com.training.library.repository.UserRepository;
import com.training.library.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public User save(UserDto userDto) {
        User user = userConverter.fromDto(userDto);
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<User> getByName(String name) {
        return userRepository.findUserByName(name);
    }
}
