package com.vti.lesson1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
//trả về dữ liệu cho phía user để in ra màn hình
public class PostDto {
    private long id;
    private String title;
    private String content;
    private  String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
