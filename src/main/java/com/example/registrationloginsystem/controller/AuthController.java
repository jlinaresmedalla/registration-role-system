package com.example.registrationloginsystem.controller;

import com.example.registrationloginsystem.dto.UserDto;
import com.example.registrationloginsystem.entity.User;
import com.example.registrationloginsystem.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserServiceImpl userServiceImpl;

    @GetMapping("/index")
    public  String index(){
        return "index";
    }

    @GetMapping("/add")
    public  String showRegistrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register-user";
    }

    @PostMapping("/add/save")
    public String  registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
        User existing = userServiceImpl.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/add";
        }
        userServiceImpl.saveUser(userDto);
        return "redirect:/add?success";
    }

}
