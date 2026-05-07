package com.vti.lesson1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
