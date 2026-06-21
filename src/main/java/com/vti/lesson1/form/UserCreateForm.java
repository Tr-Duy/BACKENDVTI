package com.vti.lesson1.form;

import com.vti.lesson1.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


//chuc nang dang ki
@Getter
@Setter
public class UserCreateForm {
    @NotBlank
    @Length(max = 50)
    private String name;
    @NotBlank
    @Length(max = 50)
    private String username;
    @Email
    @NotBlank
    @Length(max = 50)
    private String email;
    @NotBlank
    @Length(min =8,max = 32)
    private String password;
    @NotBlank
    @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER", message = "Role must be MANAGER or EMPLOYEE or ADMIN") //CHI DUOC NHAP 3 KI TU NAY THOI
    private String role;
}
