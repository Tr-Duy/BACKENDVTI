package com.vti.lesson1.controller;

import com.vti.lesson1.dto.CommentDto;
import com.vti.lesson1.form.CommentCreateForm;
import com.vti.lesson1.form.CommentUpdateForm;
import com.vti.lesson1.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // tai sao k phai controller vi restcontroller da co controller va responbody
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @GetMapping("/api/v1/comments")
    public Page<CommentDto> findAll(Pageable pageable){
        return commentService.findAll(pageable);
    }
    @GetMapping("/api/v1/comments/{id}")
    public CommentDto findById(@PathVariable("id") long id){

        return commentService.findById(id);
    }
    @ResponseStatus(HttpStatus.CREATED)// tra ve 201 neu tao moi thanh cong
    @PostMapping("/api/v1/comments")
    public CommentDto create(@RequestBody CommentCreateForm form){

        return commentService.create(form);
    }
    @PutMapping("/api/v1/comments/{id}")
    public CommentDto update(@PathVariable("id") long id, @RequestBody CommentUpdateForm form){ // restsponsbody la day du lieu len
        return commentService.update(form, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT) // neu xoa k co du lieu se bao ve loi 204
    @DeleteMapping("/api/v1/comments/{id}")
    public void deleteById(@PathVariable("id") long id){

         commentService.deleteById(id);
    }
}
