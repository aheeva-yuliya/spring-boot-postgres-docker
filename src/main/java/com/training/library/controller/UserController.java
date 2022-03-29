package com.training.library.controller;

import com.training.library.dto.UserDto;
import com.training.library.model.User;
import com.training.library.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody UserDto userDto) {
        User savedUser = userService.save(userDto);

        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        String savedUserLocation = currentUri + "/" + savedUser.getId();

        return ResponseEntity.status(CREATED)
                .header(HttpHeaders.LOCATION, savedUserLocation)
                .body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);

        return ResponseEntity.ok(user);
    }
}
