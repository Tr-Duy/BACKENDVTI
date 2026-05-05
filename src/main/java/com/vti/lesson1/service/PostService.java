package com.vti.lesson1.service;

import com.vti.lesson1.dto.PostDto;
import com.vti.lesson1.form.PostCreateForm;
import com.vti.lesson1.form.PostUpdateForm;

import java.util.List;

public interface PostService {
    // chuc nang fileAll
    List<PostDto> findAll();

    // filebyid tra ve 1 doi tuong
    PostDto findById(long id);

    //create
    PostDto create(PostCreateForm form);

    //update
    PostDto update(long id, PostUpdateForm form);

    //delete
    void deleteById(long id);
}
