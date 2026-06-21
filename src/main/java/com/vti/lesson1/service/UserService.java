package com.vti.lesson1.service;

import com.vti.lesson1.dto.UserDto;
import com.vti.lesson1.form.UserCreateForm;

public interface UserService {
    //logic tao tai khoan va ma hoa mat khau
    UserDto create(UserCreateForm form);
}
