package com.vti.lesson1.repository;

import com.vti.lesson1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {//build menhde where linh hoat phai them JpaSpecificationExecutor
    // su dung thu vien spring data cho ke thtua jpaRepo
    //2 tham so , tham so 1 la entity , tham so 2 la kieu du lieu cua khoa chinh
}
