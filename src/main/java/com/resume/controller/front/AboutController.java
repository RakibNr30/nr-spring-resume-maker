package com.resume.controller.front;

import com.resume.route.Web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = Web.ABOUT)
public class AboutController {

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "About Us");
    }

    @RequestMapping
    public String index(Model model) {
        return "front/about/index";
    }
}
