package com.MyNote.MyNote.controller;

import com.MyNote.MyNote.model.dto.UserRegistrationDTO;
import com.MyNote.MyNote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/user")
    public String addNewUser(@ModelAttribute UserRegistrationDTO dto) {

        if (!Objects.equals(dto.password(), dto.confirmPassword())) {
            return "redirect:/registration?passwordMismatch=true";
        }

        try {
            userService.addNewUser(dto);
        } catch (Exception e) {
            return "redirect:/registration?userAlreadyExist=true";
        }
        return "redirect:/login";
    }
}

