package com.vti.lesson1.repository;

import com.vti.lesson1.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    Page<Comment> findAllByPostId(Long postId, Pageable pageable); // Lấy tất cả comment thuộc bài post có id = 1, có phân trang
    @Transactional//cần tự thêm để Spring mở transaction khi thực hiện DELETE.
    void deleteAllByPostId(Long postId);
}