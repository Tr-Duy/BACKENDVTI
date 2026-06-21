package com.vti.lesson1.validation;

import com.vti.lesson1.repository.PostRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
//ten logic kiem tra
@AllArgsConstructor
// xac thuc xem bai viet co ton tai hay khong
public class PostIdExistsValidator implements ConstraintValidator<PostIdExists, Long> {
    private PostRepository postRepository;
    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {

        return postRepository.existsById(id);
        //neu yeu cau tao theo title chu khong phai id thi phai vao repository viet
    }
}
