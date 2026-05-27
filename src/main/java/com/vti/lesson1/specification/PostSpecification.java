package com.vti.lesson1.specification;
import com.vti.lesson1.entity.Post;
import com.vti.lesson1.form.PostFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalTime;
import java.util.ArrayList;
public class PostSpecification { // class chứa logic filter cho Post
    public static Specification<Post> buildSpec(PostFilterForm form) { // nhận form lọc, trả về Specification
        if (form == null) { // nếu không truyền form vào
            return null; // trả null → không lọc gì, lấy hết dữ liệu
        } else { // có form thì bắt đầu xây điều kiện lọc
            return new Specification<Post>() { // tạo anonymous class implement Specification
                @Override // ghi đè method toPredicate bắt buộc
                public @Nullable Predicate toPredicate(Root<Post> root, // root = bảng Post
                                                       CriteriaQuery<?> query, // query = câu SELECT đang chạy
                                                       CriteriaBuilder builder) { // builder = công cụ tạo điều kiện WHERE
                    var predicates = new ArrayList<Predicate>(); // tạo danh sách rỗng để chứa các điều kiện
                    String search = form.getSearch(); // lấy từ khóa tìm kiếm từ form
                    if (search != null) { // chỉ lọc nếu user có nhập từ khóa
                        var predicate = builder.like(root.get("title"), "%" + search + "%"); // WHERE title LIKE '%keyword%'
                        predicates.add(predicate); // thêm điều kiện title vào danh sách
                    }
                    var minCreatedDate = form.getMinCreatedDate(); // lấy ngày bắt đầu từ form
                    if (minCreatedDate != null) { // chỉ lọc nếu user có chọn ngày bắt đầu
                        var minCreatedAt = minCreatedDate.atStartOfDay(); // chuyển sang 00:00:00 (đầu ngày)
                        var predicate = builder.greaterThanOrEqualTo(root.get("createdAt"), minCreatedAt); // WHERE createdAt >= ngày bắt đầu
                        predicates.add(predicate); // thêm điều kiện ngày bắt đầu vào danh sách
                    }
                    var maxCreatedDate = form.getMaxCreatedDate(); // lấy ngày kết thúc từ form
                    if (maxCreatedDate != null) { // chỉ lọc nếu user có chọn ngày kết thúc
                        var maxCreatedAt = maxCreatedDate.atTime(LocalTime.MAX); // chuyển sang 23:59:59 (cuối ngày)
                        var predicate = builder.lessThanOrEqualTo(root.get("createdAt"), maxCreatedAt); // WHERE createdAt <= ngày kết thúc
                        predicates.add(predicate); // thêm điều kiện ngày kết thúc vào danh sách
                    }
                    return builder.and(predicates.toArray(new Predicate[0])); // ghép tất cả điều kiện bằng AND rồi trả về
                }
            };
        }
    }
}