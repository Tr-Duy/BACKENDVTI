package com.vti.lesson1.service;

import com.vti.lesson1.dto.CommentDto;
import com.vti.lesson1.entity.Comment;
import com.vti.lesson1.form.CommentCreateForm;
import com.vti.lesson1.form.CommentUpdateForm;
import com.vti.lesson1.mapper.CommentMappper;
import com.vti.lesson1.mapper.PostMapper;
import com.vti.lesson1.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    //findlall
    @Override
    public Page<CommentDto> findAll(Pageable pageable){
        return commentRepository.findAll(pageable)
                .map(comment -> modelMapper.map(comment, CommentDto.class));
    }
    //findbyid
    @Override
    public CommentDto findById(long id){
        return commentRepository.findById(id)
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .orElse(null);
    }
    //create

    @Override
    public CommentDto create(CommentCreateForm form){
        var comment = modelMapper.map(form, Comment.class);
        var saveComment = commentRepository.save(comment);
        return modelMapper.map(saveComment, CommentDto.class);
    }
    //update
    @Override
    public CommentDto update(CommentUpdateForm form, long id) {
        var optionnal = commentRepository.findById(id);
        if (optionnal.isEmpty()) {
            return null;
        }
        var comment = optionnal.get();
        modelMapper.map(form, comment);
        var saveComment = commentRepository.save(comment);
        return modelMapper.map(saveComment, CommentDto.class);
    }
    //delete
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}

