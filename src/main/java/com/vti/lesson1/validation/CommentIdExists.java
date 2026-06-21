package com.vti.lesson1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//ten validation
@Documented //danh dau tai lieu
@Constraint( //kiem tra xem bai viet ton tai hay khong
        validatedBy = {CommentIdExistsValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})//parameter tham so truyen vao , Field thuoc tinh
@Retention(RetentionPolicy.RUNTIME)//dung khi chay
public @interface CommentIdExists {

    String message() default "{binh luan khong ton tai}";// tra ve

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}