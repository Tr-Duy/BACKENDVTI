package com.vti.lesson1.service;

import com.vti.lesson1.dto.PostDto;
import com.vti.lesson1.entity.Post;
import com.vti.lesson1.form.PostCreateForm;
import com.vti.lesson1.form.PostUpdateForm;
import com.vti.lesson1.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

//trien khai interface
@Service //phai danh dau anomation tuong ung @Service de cho spring bt duoc day la tang service
@AllArgsConstructor // phai tao contructer 1 tham so vao chu k ra gia tri null ,bang cach su dung lombok
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;     //tangaf service phai co 1 doi tuong o tang repository
    private ModelMapper modelMapper;//tao ra 1 doi tuong models mapper

    // chuc nang fillall
    @Override
    public Page<PostDto> findAll(Pageable pageable) { // // Lấy toàn bộ bài viết từ database Kết quả: List<Post> = [Post1, Post2, Post3, ...]
        return postRepository.findAll(pageable)
                .map(post -> modelMapper.map(post, PostDto.class));  // dung lamda , anh xa tu entity sang dto
    }
    // findbyid tra ve 1 doi tuong
    @Override
    public PostDto findById(long id) {
        return postRepository.findById(id) // Tìm bài viết theo ID
                .map(post -> modelMapper.map(post, PostDto.class)) // Chuyển từ Post sang PostDto
                .orElse(null); // Nếu không tìm thấy thì trả về null
    }
    //create
    @Override
    public PostDto create(PostCreateForm form) {
        var post = modelMapper.map(form, Post.class);
        var savePost = postRepository.save(post);
        return modelMapper.map(savePost, PostDto.class);
    }
    //update
    @Override
    public PostDto update(long id, PostUpdateForm form) {
        var optional = postRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        var post = optional.get();
        modelMapper.map(form, post);
        var savePost = postRepository.save(post);
        return modelMapper.map(savePost, PostDto.class);
    }
    //delete
    @Override
    public void deleteById(long id) {
        postRepository.deleteById(id);
    }
}

