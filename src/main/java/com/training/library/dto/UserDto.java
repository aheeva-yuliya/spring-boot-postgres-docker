package com.training.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@ToString
public class UserDto {
    @Email(message = "Enter valid email")
    private String name;

    @Size(min = 3, max = 64, message = "Password must be between 3 and 64")
    private String password;
}
