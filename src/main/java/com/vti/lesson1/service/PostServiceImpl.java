package com.vti.lesson1.service;

import com.vti.lesson1.dto.PostDto;
import com.vti.lesson1.form.PostCreateForm;
import com.vti.lesson1.form.PostUpdateForm;
import com.vti.lesson1.mapper.PostMapper;
import com.vti.lesson1.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//trien khai interface
@Service //phai danh dau anomation tuong ung @Service de cho spring bt duoc day la tang service
@AllArgsConstructor // phai tao contructer 1 tham so vao chu k ra gia tri null ,bang cach su dung lombok
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;     //tangaf service phai co 1 doi tuong o tang repository

    // chuc nang fileAll
    @Override
    public List<PostDto> findAll() { // // Lấy toàn bộ bài viết từ database Kết quả: List<Post> = [Post1, Post2, Post3, ...]
        return postRepository.findAll()
                .stream()// Chuyển List thành "băng chuyền" để xử lý từng phần tử
                .map(PostMapper::map)// // Với mỗi Post → chạy hàm PostMapper.map() → ra PostDto
                .toList(); // Gom tất cả PostDto lại thành 1 List
    }
    // filebyid tra ve 1 doi tuong
    @Override
    public PostDto findById(long id) {
        return postRepository.findById(id) // Tìm bài viết theo ID
                .map(PostMapper::map) // Chuyển từ Post sang PostDto
                .orElse(null); // Nếu không tìm thấy thì trả về null
    }
    //create
    @Override
    public PostDto create(PostCreateForm form) { // dau vao form dau ra dto
        var post = PostMapper.map(form); // Dùng mapper để chuyển PostDto → Post
        var savePost = postRepository.save(post); // Lưu vào DB, trả về bài viết đã được lưu
        return PostMapper.map(savePost); // Chuyển lại từ Post → PostDto và trả về
    }
    //update
    @Override
    public PostDto update(long id, PostUpdateForm form) { //optional boc toan bo bai viet
        var optional = postRepository.findById(id); // Tìm bài viết theo ID
        if (optional.isEmpty()) {
            return null; // Không tìm thấy bài viết → không thể update
        }
      var post = optional.get(); // lay ra bai viet tu optional ,bai viet da co trong dtb , nhung kdl la entity
        PostMapper.map(form, post); //  anh xa du lieu tu form update sang dtb
        var savePost = postRepository.save(post); // Lưu lại vào DB
        return PostMapper.map(savePost); // Trả về PostDto đã được cập nhật
    }
    //delete
    @Override
    public void deleteById(long id) {
        postRepository.deleteById(id);
    }
}

