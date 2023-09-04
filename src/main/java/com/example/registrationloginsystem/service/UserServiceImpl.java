package com.example.registrationloginsystem.service;

import com.example.registrationloginsystem.dto.UserDto;
import com.example.registrationloginsystem.entity.Role;
import com.example.registrationloginsystem.entity.User;
import com.example.registrationloginsystem.repository.RoleRepository;
import com.example.registrationloginsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public void saveUser(UserDto userDto) {
        User newUser = new User(userDto);
        Role checkedRole = checkRole("ROLE_ADMIN");
        newUser.setRoles(Arrays.asList(checkedRole));
        userRepository.save(newUser);
    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private Role checkRole(String name){
        Role role = roleRepository.findByName(name);
        if (role == null){
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

}
