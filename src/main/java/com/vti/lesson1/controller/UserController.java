package com.vti.lesson1.controller;

import com.vti.lesson1.dto.UserDto;
import com.vti.lesson1.form.UserCreateForm;
import com.vti.lesson1.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/api/v1/auth/register")// xac thuc thongg tin dang ki
    public UserDto create(@RequestBody @Valid UserCreateForm form){
        return userService.create(form);
    }
}
