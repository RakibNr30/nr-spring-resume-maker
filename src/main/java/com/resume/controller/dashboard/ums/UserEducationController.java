package com.resume.controller.dashboard.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.ums.User;
import com.resume.entity.ums.UserEducation;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.ums.UserEducationService;
import com.resume.service.ums.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/dashboard/ums/user-education")
public class UserEducationController {

    private final UserService userService;

    private final UserEducationService userEducationService;

    @Autowired
    public UserEducationController(UserService userService, UserEducationService userEducationService) {
        this.userService = userService;
        this.userEducationService = userEducationService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "User Education");
    }

    @ModelAttribute
    public UserEducation getUserEducation() {
        return new UserEducation();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<UserEducation> userEducations = this.userEducationService.findPaginated(pageRequestDto);
        model.addAttribute("userEducations", userEducations);

        return "dashboard/ums/user-education/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        List<User> users = this.userService.findAll();

        model.addAttribute("users", users);
        return "dashboard/ums/user-education/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute UserEducation userEducation, BindingResult result, @RequestParam("user_id") Long userId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userEducation", userEducation).bind(result);
            return "redirect:/dashboard/ums/user-education/create";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        try {
            userEducation.setUser(user);
            this.userEducationService.save(userEducation);
            new NotifierHelper(attributes).message("User education added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("User education can not be added.").error();
            return "redirect:/dashboard/ums/user-education/create";
        }

        return "redirect:/dashboard/ums/user-education";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        UserEducation userEducation = this.userEducationService.findById(id);

        if (userEducation == null) {
            new NotifierHelper(attributes).message("User education not found.").error();
            return "redirect:/dashboard/ums/user-education";
        }

        model.addAttribute("userEducation", userEducation);

        return "dashboard/ums/user-education/show";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        UserEducation userEducation = this.userEducationService.findById(id);

        if (userEducation == null) {
            new NotifierHelper(attributes).message("User education not found.").error();
            return "redirect:/dashboard/ums/user-education";
        }

        List<User> users = this.userService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("userEducation", userEducation);

        return "dashboard/ums/user-education/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute UserEducation userEducation, BindingResult result, @RequestParam("user_id") Long userId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userEducation", userEducation).bind(result);
            return "redirect:/dashboard/ums/user-education/" + id + "/edit";
        }

        UserEducation updatableUserEducation = this.userEducationService.findById(id);

        if (updatableUserEducation == null) {
            new NotifierHelper(attributes).message("User education not found.").error();
            return "redirect:/dashboard-education/user";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        try {
            userEducation.setUser(user);
            this.userEducationService.update(userEducation);
            new NotifierHelper(attributes).message("User education updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User education can not be updated.").error();
        }

        return "redirect:/dashboard/ums/user-education/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        UserEducation userEducation = this.userEducationService.findById(id);

        if (userEducation == null) {
            new NotifierHelper(attributes).message("User education not found.").error();
        }

        try {
            this.userEducationService.delete(userEducation);
            new NotifierHelper(attributes).message("User education deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User education can not ber deleted.").error();
        }

        return "redirect:/dashboard/ums/user-education";
    }
}
