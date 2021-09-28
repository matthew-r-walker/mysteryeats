package com.mysteryeats.mysteryeats.controllers;

import com.mysteryeats.mysteryeats.models.User;
import com.mysteryeats.mysteryeats.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            return "redirect:/profile";
        }
        model.addAttribute("user", new User());
        return "users/signup";
    }

    @PostMapping("/signup")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result, @RequestParam String signUpPasswordConfirm) {
        String hash = passwordEncoder.encode(user.getPassword());
        if (usersDao.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "user.email", "This email already exists");
        }
        if (usersDao.existsByUsername(user.getUsername())) {
            result.rejectValue("username", "user.username", "This username already exists");
        }

        if (!signUpPasswordConfirm.equals(user.getPassword())) {
            result.rejectValue("password", "user.password", "Passwords do not match");
        }

        if (result.hasErrors()) {
            return "users/signup";
        }
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/";
    }
}
