package com.vti.lesson1.config;

import com.vti.lesson1.dto.CommentDto;
import com.vti.lesson1.entity.Comment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIContainer {
    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        modelMapper.typeMap(Comment.class, CommentDto.class)
                .addMappings(mapper -> mapper.skip(CommentDto::setCreateAt));
        return modelMapper;
    }
}
