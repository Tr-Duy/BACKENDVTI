package com.vti.lesson1.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateForm {
    @NotBlank
    private String name;
    @Email // kiem tra dinh dang email phai co @
    @NotBlank
    private String email;
    @NotBlank
    private String body;
}

