package com.vti.lesson1.form;
// nhìn qua entity ể xem cần nhuững gì

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostCreateForm {
    @NotBlank(message = "Title is required")//tieu de khong dc de trong
    @Length(max = 50 , message = "Title is not exceed 50 characters")// tieu de toi da 50 chu
    private String title;
    @NotBlank(message = "noi dung is required")//noi dung khong dc de trong
    @Length(max = 100 , message = "noi dung is not exceed 50 characters")// noi dung toi da 50 chu
    private String content;
    @NotBlank(message = "description is required")//  mo ta khong dc de trong
    @Length(max = 150 , message = "description is not exceed 50 characters")// mo ta toi da 150 chu
    private String description;

}
