package com.github.truongbb.tinytaskmanagement.controller;

import com.github.truongbb.tinytaskmanagement.entity.User;
import com.github.truongbb.tinytaskmanagement.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @GetMapping
    public String getAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users_option/new-task";
    }

}
