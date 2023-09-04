package com.example.registrationloginsystem.service;

import com.example.registrationloginsystem.dto.UserDto;
import com.example.registrationloginsystem.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);
    UserDto findById(Long id);
    User findByEmail(String email);
    List<UserDto> findAll();
    UserDto update(Long id,UserDto userDto);
    void delete(Long id);

}
