package com.vti.lesson1.service;

import com.vti.lesson1.dto.UserDto;
import com.vti.lesson1.entity.User;
import com.vti.lesson1.form.UserCreateForm;
import com.vti.lesson1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService , UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;//ma hoa mat khau
    private ModelMapper modelMapper;
    //logic tao tai khoan va ma hoa mat khau
    @Override
    public UserDto create(UserCreateForm form){//fom tao tai khoan tra ve thong tin
            var user = modelMapper.map(form, User.class);
            var encodedPassword = passwordEncoder.encode(form.getPassword());//ma hoa
            user.setPassword(encodedPassword);//set lai mat khau da duoc ma hoa
        var savedUser = userRepository.save(user);//luu vo database
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//dang nhap , nhap vao tt tk mk
        var user = userRepository.findByUsernameOrEmail(username,username);//kiem xem co thong tin ve nguoi dung hay khong
        if(user == null){ //neu khong co tra ve 401
            throw new UsernameNotFoundException(username);
        }
        //kiem tra xem tai khoan mat khau dung hay chua
        var role = user.getRole().name();//admin
        var authorities = AuthorityUtils.createAuthorityList(role);//neu khong khop tra ve 401 con khop lay du lieu dtb tra ve
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities); //neu co thi tra ve tk mk vaitro
    }
}
