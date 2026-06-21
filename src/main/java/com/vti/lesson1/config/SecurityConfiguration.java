package com.vti.lesson1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//cau hinh security
@Configuration

public class SecurityConfiguration {
    //cau hinh chuc nang dang ki khong can tao tai khoan

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ){
        http
                .csrf(AbstractHttpConfigurer::disable)//tat tinh nang csrf di
                .cors(Customizer.withDefaults())//cau hinh mac dinh
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS.STATELESS)//khong luu trang thai
                )
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll() //tat ca ai
                        // cung dang ki dc
                        .anyRequest() //con lai thi tat ca deu phai dang nhap
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());//bat bo loc basic frontend
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
