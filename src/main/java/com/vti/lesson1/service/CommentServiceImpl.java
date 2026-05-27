package com.vti.lesson1.service;

import com.vti.lesson1.dto.CommentDto;
import com.vti.lesson1.entity.Comment;
import com.vti.lesson1.form.CommentCreateForm;
import com.vti.lesson1.form.CommentFilterForm;
import com.vti.lesson1.form.CommentUpdateForm;
import com.vti.lesson1.mapper.CommentMappper;
import com.vti.lesson1.mapper.PostMapper;
import com.vti.lesson1.repository.CommentRepository;
import com.vti.lesson1.repository.PostRepository;
import com.vti.lesson1.specification.CommentSpecification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;//truy cap vao commentrepository de lay ra bai viet
    private ModelMapper modelMapper;//su dung model mapper phai add oo
    private PostRepository postRepository;//muon kiem tra post ton tai hay khong phai truy cap vao repository cua bai viet

    //findlall
    @Override
    public Page<CommentDto> findAll(CommentFilterForm form, Pageable pageable){
        var spec = CommentSpecification.buildSpec(form);//chuyen form sang menh de where
        return commentRepository.findAll(spec,pageable) //truyen tham so spec vao findall
                .map(comment -> modelMapper.map(comment, CommentDto.class));
    }
    //findbyid
    @Override
    public CommentDto findById(long id){
        return commentRepository.findById(id)
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .orElse(null);
    }
    //lay ra tất cả comment có trong bvieeseieest dựa vào id của bài viết
    @Override //chuc nang khong co san trong JpaRepository phải bỏ vô Repository
        public Page<CommentDto> findAllByPostId(Long postId, Pageable pageable) {
        return commentRepository.findAllByPostId(postId, pageable)
                .map(comment -> modelMapper.map(comment, CommentDto.class));
    }

    //create

    @Override
    public CommentDto create(CommentCreateForm form, Long postId){
        var optional = postRepository.findById(postId);//kiem tra bai viet co ton tai hay k
        if (optional.isEmpty()) {
            return null;
        }
        var post = optional.get(); //neu ton tai thi lay ra bai viet
        var comment = modelMapper.map(form, Comment.class);
        comment.setPost(post);//set thong tin comment thuoc ve bai viet nay
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

    //xoa tat ca comment cua 1 bai viet
    @Override
    @Transactional//dam bao cac comment bi xoa hoac khong cai nao bi xoa
    public void deleteAllByPostId(Long postId) {
        commentRepository.deleteAllByPostId(postId);
    }

}

