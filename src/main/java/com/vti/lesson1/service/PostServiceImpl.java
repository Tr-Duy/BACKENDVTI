package com.vti.lesson1.service;

import com.vti.lesson1.dto.PostDto;
import com.vti.lesson1.entity.Post;
import com.vti.lesson1.form.PostCreateForm;
import com.vti.lesson1.form.PostFilterForm;
import com.vti.lesson1.form.PostUpdateForm;
import com.vti.lesson1.repository.PostRepository;
import com.vti.lesson1.specification.PostSpecification;
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
    public Page<PostDto> findAll(PostFilterForm form,Pageable pageable) { // // Lấy toàn bộ bài viết từ database Kết quả: List<Post> = [Post1, Post2, Post3, ...]
        var spec = PostSpecification.buildSpec(form);//build ra menh de where dung tren form tren repository
        return postRepository.findAll(spec,pageable) //truyen vao tham so vao ham findall
                .map(post -> modelMapper.map(post, PostDto.class));  // dung lamda , anh xa tu entity sang dto
    }
    // findbyid tra ve 1 doi tuong
    @Override
    public PostDto findById(long id) {
        return postRepository.findById(id) // Tìm bài viết theo ID
                .map(post -> modelMapper.map(post, PostDto.class)) // Chuyển từ Post sang PostDto
                .orElse(null); // Nếu không tìm thấy thì trả về null
    }
    //create:form (dữ liệu client) → Post → lưu DB → PostDto → trả về
    @Override
    public PostDto create(PostCreateForm form) {
        var post = modelMapper.map(form, Post.class);// Convert form (dữ liệu từ client gửi lên) thành object Post
        var savePost = postRepository.save(post);//Lưu post vào DB, trả về post đã có id (do DB tự sinh)
        return modelMapper.map(savePost, PostDto.class); // Convert Post → PostDto rồi trả về cho client
    }
    //update:Nhận id + form
    //   → Tìm post trong DB
    //   → Không có → return null
    //   → Có → ghi đè dữ liệu mới → save → trả về DTO
    @Override
    public PostDto update(long id, PostUpdateForm form) {
        var optional = postRepository.findById(id); // Tìm bài post theo id trong DB, trả về Optional
        if (optional.isEmpty()) { //Nếu không tìm thấy bài post → trả về null (không làm gì cả)
            return null;
        }
        var post = optional.get(); //  Nếu tìm thấy → lấy object Post ra khỏi Optional
        modelMapper.map(form, post); // Copy dữ liệu từ form (dữ liệu mới) vào post (object cũ)
        var savePost = postRepository.save(post); //Lưu post đã cập nhật vào DB
        return modelMapper.map(savePost, PostDto.class);//Convert Post → PostDto rồi trả về cho client

    }
    //delete  Gọi thẳng deleteById(id) của repository để xóa bài post theo id trong DB.Gọi thẳng deleteById(id) của repository để xóa bài post theo id trong DB.
    //Không cần kiểm tra tồn tại, không trả về gì (void).
    @Override
    public void deleteById(long id) {
        postRepository.deleteById(id);
    }
}

