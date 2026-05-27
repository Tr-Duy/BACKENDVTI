package com.vti.lesson1.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//form cho nguoi dung loc du lieu
@Getter
@Setter
public class PostFilterForm {
    private String search;
    private LocalDate minCreatedDate;
    private LocalDate maxCreatedDate;
}
