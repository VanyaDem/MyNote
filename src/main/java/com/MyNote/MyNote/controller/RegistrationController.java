package com.MyNote.MyNote.controller;

import com.MyNote.MyNote.model.entity.User;
import com.MyNote.MyNote.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRepository userRepository;

    @PostMapping("/user")
    private String addNewUser(Model model){
        var user = (User)model.getAttribute("newUser");
        userRepository.save(Objects.requireNonNull(user));
        return "redirect:/login";
    }
}
