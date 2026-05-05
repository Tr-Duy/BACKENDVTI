package com.vti.lesson1.controller;

import com.vti.lesson1.dto.PostDto;
import com.vti.lesson1.form.PostCreateForm;
import com.vti.lesson1.form.PostUpdateForm;
import com.vti.lesson1.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //danh dau cho spring biet day la controller
@AllArgsConstructor //tao contructor day du tham so

public class PostController {
    private PostService postService; // can doi tuong service
 //dua vao PostServiceImpl de goi api
    @GetMapping("/api/v1/posts") // duong dan api
    public List<PostDto> findAll(){
        return postService.findAll(); //goi ham findAll trong service
    }
    @GetMapping("/api/v1/posts/{id}") // lay du lieu duong dan api , lay id {id} bang bao nhieu
    public PostDto findById(@PathVariable("id") long id){ // lay con so tren duong dan bang @PathVariable
        return postService.findById(id);
    }
    @PostMapping("/api/v1/posts")
    public PostDto create(@RequestBody PostCreateForm form){ //requestbody day du lieu len tren server
        return postService.create(form);
    }
    @PutMapping("/api/v1/posts/{id}")
    public PostDto update(@PathVariable("id") long id, @RequestBody PostUpdateForm form){
        return postService.update(id,form);
    }
    @DeleteMapping("/api/v1/posts/{id}")
    public void deleteById(@PathVariable("id") long id){
        postService.deleteById(id);
    }
}
