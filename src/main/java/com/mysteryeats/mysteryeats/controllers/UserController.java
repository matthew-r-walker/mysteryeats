package com.mysteryeats.mysteryeats.controllers;

import com.mysteryeats.mysteryeats.models.User;
import com.mysteryeats.mysteryeats.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private UserRepository usersDao;

    public UserController(UserRepository usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            return "redirect:/profile";
        }
        model.addAttribute("user", new User());
        return "users/signup";
    }
}
