package com.vti.lesson1.specification;

import com.vti.lesson1.entity.Comment;
import com.vti.lesson1.form.CommentFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class CommentSpecification {
    public static Specification<Comment> buildSpec(CommentFilterForm form) {
        return form == null ? null : new Specification<Comment>() { // neu form null thi khong loc, nguoc lai tao specification
            @Override // ghi de method toPredicate cua Specification
            public @Nullable Predicate toPredicate(
                    Root<Comment> root, // root dai dien cho bang Comment trong query
                    CriteriaQuery<?> query, // query la cau SELECT dang duoc chay
                    CriteriaBuilder builder // builder la cong cu de tao dieu kien WHERE
            ) {
                var predicates = new ArrayList<Predicate>(); // tao danh sach rong chua cac dieu kien loc
                //loc comment theo ho va ten
                var search = form.getSearch(); // lay tu khoa tim kiem tu form
                if(search != null && !search.isEmpty()){ // chi loc neu tu khoa khong null va khong rong
                    //where name like %...% OR email Like %..%
                    var hasNameLike = builder.like(root.get("name"), "%" + search + "%"); // dieu kien: name chua tu khoa
                    var hasEmailLike = builder.like(root.get("email"), "%" + search + "%"); // dieu kien: email chua tu khoa
                    var predicate = builder.or(hasNameLike, hasEmailLike); //lien ket qua tu khoa or
                    predicates.add(predicate); // them dieu kien OR vua tao vao danh sach
                }
                //lay ra comment theo bai viet nao do
                var postId = form.getPostId(); // lay id bai viet can loc comment
                if(postId != null){ // chi loc neu co truyen postId
                    var predicate = builder.equal(root.get("post").get("id"), postId); //rooot ... long nhau -- truy cap post.id cua comment de so sanh
                    predicates.add(predicate); // them dieu kien postId vao danh sach
                }
                return builder.and(predicates.toArray(new Predicate[0])); //cu phap chuyen hoa list sang mang -- chuyen List thanh mang roi gop tat ca dieu kien bang AND tra ve
            }
        };
    }
}
