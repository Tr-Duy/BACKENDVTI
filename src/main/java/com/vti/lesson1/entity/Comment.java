package com.vti.lesson1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
@JsonIgnoreProperties({"post"})//dat ten bang dtb
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

    @Column(name = "create_at", nullable = false, updatable = false) // khong duoc update thoi gian
    @CreationTimestamp //lay thoi gian hien tai
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @ManyToOne // nhieu comment thuoc 1 bai post
    @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "id")

    //cau hinh khi xoa 1 post thi comment cung bi xoa theo
    @OnDelete(action = OnDeleteAction.CASCADE) // CASCADE KHI CHA BI XOA THI CON CUNG XOA THEO
    private Post post; // bai viet thuoc ve thuoc tinh post kieu du lieu post
}
