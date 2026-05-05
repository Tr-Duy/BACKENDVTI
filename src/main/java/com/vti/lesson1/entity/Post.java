package com.vti.lesson1.entity;
// class đại diện cho 1 bảng trong dtb
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
}
