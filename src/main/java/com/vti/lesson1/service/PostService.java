package com.vti.lesson1.service;

import com.vti.lesson1.dto.PostDto;
import com.vti.lesson1.form.PostCreateForm;
import com.vti.lesson1.form.PostFilterForm;
import com.vti.lesson1.form.PostUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    // chuc nang fileAll ,   loc tren findall
    Page<PostDto> findAll(PostFilterForm form, Pageable pageable);//chon page<T> chon lien quan den spring
    // filebyid tra ve 1 doi tuong
    PostDto findById(long id);

    //create
    PostDto create(PostCreateForm form);

    //update
    PostDto update(long id, PostUpdateForm form);

    //delete
    void deleteById(long id);
}
