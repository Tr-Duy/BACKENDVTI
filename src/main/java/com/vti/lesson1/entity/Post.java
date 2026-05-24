package com.vti.lesson1.entity;
// class đại diện cho 1 bảng trong dtb
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity // bang trong dtb bt su dung bang post su dung anomation
@Table(name = "post") // dung de dat ten bang
public class Post {
    @Id //khoa chinh su dung anotation
    @Column(name = "id") //dat ten cot su dung anotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // su dung tinh chat cua id tu dong tang
    private long id;

    @Column(name = "title" , length = 50, nullable = false) // khong dc phep null la dung nullable
    private String title;

    @Column(name = "content", length = 150 , nullable = false)
    private String content;

    @Column(name = "description", length = 100 , nullable = false)
    private String description;

    @Column (name = "created_at", nullable = false , updatable = false) // updateable = false là thời gian tạo k đc sửa
    @CreationTimestamp     // lay tg hien tai cap nhat bai viet nguoi dung k can tao
    private LocalDateTime createdAt;

    @Column( name = "updated_at", nullable = false)
    @UpdateTimestamp     // lay tg hien tai khi update bai viet
    private  LocalDateTime updatedAt;

    //comment da có thông tin ve bài post thi gio post cũng phải có thông tin về comment
    @OneToMany (mappedBy = "post")// 1 post  co nhieu comment , mappedBy để tên theo bên comment
    private List<Comment> comments;     // 1 bài post phải có nhiều comment nên sử dụng List , comments để số nhiều
}
