package com.resume.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    @ModelAttribute
    public void commonData(Model model) {
        model.addAttribute("title", "Error");
    }
    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "front/error/access-denied";
    }
}
