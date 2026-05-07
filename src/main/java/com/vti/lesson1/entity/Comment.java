package com.vti.lesson1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity//de bt su dung dtb comment
@Table(name = "comment")//dat ten bang dtb
public class Comment {
    @Id //khoa chinh id
    @Column(name = "id") //dat ten bang id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //su dung tinh chat tang dan id
    private long id;

    @Column(name = "name", length = 50 , nullable = false)//nullable khong duoc de trong
    private String name;

    @Column(name = "email", length = 75, nullable = false)
    private String email;

    @Column(name = "body", length = 100, nullable = false)
    private String body;

    @Column(name = "created_at", nullable = false, updatable = false) // khong duoc update thoi gian
    @CreationTimestamp //lay thoi gian hien tai
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
