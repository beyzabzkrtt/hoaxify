package com.hoaxify.ws.user.dto;

import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.validation.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(


@NotBlank(message = "{hoaxify.constraints.username.NotBlank}")
@Size(min = 4,max=10)
String username,

@NotBlank(message = "{hoaxify.constraints.email.NotBlank}")
@Email
@UniqueEmail
String email,


@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$" , message = "{hoaxify.constraint.password.pattern}" )
@Size(max = 255,min = 8)
String password) {
    
    public User toUser(){

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        return user;

    }

}
