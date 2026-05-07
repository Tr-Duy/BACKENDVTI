package com.vti.lesson1.service;

import com.vti.lesson1.dto.CommentDto;
import com.vti.lesson1.form.CommentCreateForm;
import com.vti.lesson1.form.CommentUpdateForm;
import com.vti.lesson1.mapper.CommentMappper;
import com.vti.lesson1.mapper.PostMapper;
import com.vti.lesson1.repository.CommentRepository;
import lombok.AllArgsConstructor;
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

    //findlall
    @Override
    public Page<CommentDto> findAll(Pageable pageable){
        return commentRepository.findAll(pageable)
                .map(CommentMappper::map);
    }
    //findbyid
    @Override
    public CommentDto findById(long id){
        return commentRepository.findById(id)
                .map(CommentMappper::map)
                .orElse(null);
    }
    //create

    @Override
    @ResponseStatus(HttpStatus.CREATED)// tra ve 201 neu tao moi thanh cong
    public CommentDto create(CommentCreateForm form){
        var comment = CommentMappper.map(form);//anh xa tu form sang entity
        var saveComment = commentRepository.save(comment);  // luu vao dtb , luu tc thi se tra ve savecomment nhung van o entity
        return CommentMappper.map(saveComment);// chuyen tu entity chuyen sang dto tra ve phia ng dung
    }
    //update
    @Override
    public CommentDto update(CommentUpdateForm form, long id) {
        var optionnal = commentRepository.findById(id);// tim kiem tren id
        if (optionnal.isEmpty()) { //neu khong co thi return null
            return null;
        }
        var comment = optionnal.get();//lay danh sach
        CommentMappper.map(form, comment);//truyen thong tin ng dung goi len , map tt nguoi dung da cap nhat
        var saveComment = commentRepository.save(comment);//luu vao dtb dang o entity
        return CommentMappper.map(saveComment); // anh xa entity sang dto va tra ve ng dung
    }
    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT) // neu xoa k co du lieu se bao ve loi 204
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}

