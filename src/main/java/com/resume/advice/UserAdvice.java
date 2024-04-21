package com.resume.advice;

import com.resume.service.ums.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserAdvice {

    private final UserService userService;

    public UserAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void globalAuthUserAttributes(Model model, Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            model.addAttribute("globalAuthUser", this.userService.findByUsername(auth.getName()));
        }
    }
}
