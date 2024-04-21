package com.resume.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "Dashboard");
    }

    @RequestMapping
    public String index(Model model) {
        return "dashboard/index";
    }
}
