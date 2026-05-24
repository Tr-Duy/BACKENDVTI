package com.vti.lesson1.service;

import com.vti.lesson1.dto.CommentDto;
import com.vti.lesson1.form.CommentCreateForm;
import com.vti.lesson1.form.CommentUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    //findall
    Page<CommentDto> findAll(Pageable pageable);

    //findbyid
    CommentDto findById(long id);

    Page<CommentDto> findAllByPostId(Long postId, Pageable pageable);


    //create
    CommentDto create(CommentCreateForm form, Long postId);

    //update
    CommentDto update(CommentUpdateForm form, long id);

    //delete
    void deleteById(long id);
}
