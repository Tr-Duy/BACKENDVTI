package com.vti.lesson1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

// phan hoi loi tra ve ben phia nguoi dung
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String timestamp;//thoi dien xay ra loi
    private String message;//day la loi gi
    private Map<String, String> details;// chi tiet loi

    public ErrorResponse(String message, Map<String, String> details) {
        this.timestamp = LocalDateTime.now().toString(); //lay thoi diem hien tai
        this.message = message;
        this.details = details;
    }
}
