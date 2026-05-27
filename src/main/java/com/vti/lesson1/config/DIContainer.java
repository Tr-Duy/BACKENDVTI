package com.vti.lesson1.config;
//chuc nang 1. Tạo bean ModelMapper cho Spring , 2. Cấu hình ModelMapper
import com.vti.lesson1.dto.CommentDto;
import com.vti.lesson1.entity.Comment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // đây là file cấu hình
public class DIContainer {
    @Bean           // đăng ký ModelMapper vào Spring Container
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        // Chiến lược mapping: khớp tên field theo chuẩn
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        // Khi map Comment → CommentDto thì BỎ QUA field createAt
        modelMapper.typeMap(Comment.class, CommentDto.class)
                .addMappings(mapper -> mapper.skip(CommentDto::setCreateAt));
        return modelMapper;
    }
}
