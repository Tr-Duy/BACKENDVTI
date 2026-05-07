package com.vti.lesson1.mapper;

import com.vti.lesson1.dto.CommentDto;
import com.vti.lesson1.entity.Comment;
import com.vti.lesson1.form.CommentCreateForm;
import com.vti.lesson1.form.CommentUpdateForm;
import com.vti.lesson1.form.PostUpdateForm;

public class CommentMappper {
    public static CommentDto map(Comment comment){
        var dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getName());
        dto.setBody(comment.getBody());
        dto.setCreateAt(comment.getCreatedAt());
        dto.setUpdateAt(comment.getUpdateAt());
        return dto;
    }
    //anh xa tu create sang entity
    public static Comment map(CommentCreateForm form){
        var comment = new Comment();
        comment.setName(form.getName());
        comment.setEmail(form.getEmail());
        comment.setBody(form.getBody());
        return comment;
    }
    //update
    public static void map(CommentUpdateForm form, Comment comment){
        comment.setName(form.getName());
        comment.setEmail(form.getEmail());
        comment.setBody(form.getBody());
    }
}
