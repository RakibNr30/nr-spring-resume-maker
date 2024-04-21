package com.resume.controller.dashboard.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.ums.User;
import com.resume.entity.ums.UserExperience;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.ums.UserExperienceService;
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
@RequestMapping("/dashboard/ums/user-experience")
public class UserExperienceController {

    private final UserService userService;

    private final UserExperienceService userExperienceService;

    @Autowired
    public UserExperienceController(UserService userService, UserExperienceService userExperienceService) {
        this.userService = userService;
        this.userExperienceService = userExperienceService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "User Experience");
    }

    @ModelAttribute
    public UserExperience getUserExperience() {
        return new UserExperience();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<UserExperience> userExperiences = this.userExperienceService.findPaginated(pageRequestDto);
        model.addAttribute("userExperiences", userExperiences);

        return "dashboard/ums/user-experience/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        List<User> users = this.userService.findAll();

        model.addAttribute("users", users);
        return "dashboard/ums/user-experience/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute UserExperience userExperience, BindingResult result, @RequestParam("user_id") Long userId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userExperience", userExperience).bind(result);
            return "redirect:/dashboard/ums/user-experience/create";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        try {
            userExperience.setUser(user);
            this.userExperienceService.save(userExperience);
            new NotifierHelper(attributes).message("User experience added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("User experience can not be added.").error();
            return "redirect:/dashboard/ums/user-experience/create";
        }

        return "redirect:/dashboard/ums/user-experience";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        UserExperience userExperience = this.userExperienceService.findById(id);

        if (userExperience == null) {
            new NotifierHelper(attributes).message("User experience not found.").error();
            return "redirect:/dashboard/ums/user-experience";
        }

        model.addAttribute("userExperience", userExperience);

        return "dashboard/ums/user-experience/show";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        UserExperience userExperience = this.userExperienceService.findById(id);

        if (userExperience == null) {
            new NotifierHelper(attributes).message("User experience not found.").error();
            return "redirect:/dashboard/ums/user-experience";
        }

        List<User> users = this.userService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("userExperience", userExperience);

        return "dashboard/ums/user-experience/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute UserExperience userExperience, BindingResult result, @RequestParam("user_id") Long userId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userExperience", userExperience).bind(result);
            return "redirect:/dashboard/ums/user-experience/" + id + "/edit";
        }

        UserExperience updatableUserExperience = this.userExperienceService.findById(id);

        if (updatableUserExperience == null) {
            new NotifierHelper(attributes).message("User experience not found.").error();
            return "redirect:/dashboard-experience/user";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        try {
            userExperience.setUser(user);
            this.userExperienceService.update(userExperience);
            new NotifierHelper(attributes).message("User experience updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User experience can not be updated.").error();
        }

        return "redirect:/dashboard/ums/user-experience/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        UserExperience userExperience = this.userExperienceService.findById(id);

        if (userExperience == null) {
            new NotifierHelper(attributes).message("User experience not found.").error();
        }

        try {
            this.userExperienceService.delete(userExperience);
            new NotifierHelper(attributes).message("User experience deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User experience can not ber deleted.").error();
        }

        return "redirect:/dashboard/ums/user-experience";
    }
}
