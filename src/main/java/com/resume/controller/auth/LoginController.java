package com.resume.controller.auth;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {
    @ModelAttribute
    public void commonData(Model model) {
        model.addAttribute("title", "Login");
    }

    @RequestMapping("/login")
    public String login(Model model, Authentication auth) {

        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/";
        }

        return "auth/login";
    }
}
