package com.vti.lesson1.exception;
//: xử lý ngoại lệ và trả về theo định dạng ErrorResponse (sử lý lỗi)

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;

@ControllerAdvice//dah dau class la class xu ly loi
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid( // xu ly ngoai lehandleMethodA
            MethodArgumentNotValidException ex
            , HttpHeaders headers
            , HttpStatusCode status
            , WebRequest request) {
        var message = "du lieu khong hop le"; // du lieu khong hop le
        var details = new LinkedHashMap<String, String>(); //chi tiet loi tao ra 1 list hash map
        for(var error : ex.getFieldErrors()){//fieldErrors: khong duoc de trong
            //chay het vong lap se co
            var key = error.getField(); // key la title khong duoc de trong
            var value = error.getDefaultMessage(); //toi da 150 ki tu
            details.put(key, value);
        }
        var errorResponse = new ErrorResponse(message, details);
        return new ResponseEntity<>(errorResponse, headers, status); // body, headers, status
    }
}
