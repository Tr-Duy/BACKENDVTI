package com.vti.lesson1.repository;

import com.vti.lesson1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email); //kiem tra dang nhap
}
