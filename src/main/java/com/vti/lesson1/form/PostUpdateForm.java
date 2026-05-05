package com.vti.lesson1.form;
// nhìn qua entity ể xem cần nhuững gì

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateForm {
    private String title;
    private String content;
    private String description;
}
