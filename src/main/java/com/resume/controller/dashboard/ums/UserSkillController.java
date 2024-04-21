package com.resume.controller.dashboard.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.Skill;
import com.resume.entity.ums.User;
import com.resume.entity.ums.UserSkill;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.cms.SkillService;
import com.resume.service.ums.UserSkillService;
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
@RequestMapping("/dashboard/ums/user-skill")
public class UserSkillController {

    private final UserService userService;

    private final SkillService skillService;

    private final UserSkillService userSkillService;

    @Autowired
    public UserSkillController(UserService userService, SkillService skillService, UserSkillService userSkillService) {
        this.userService = userService;
        this.skillService = skillService;
        this.userSkillService = userSkillService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "User Skill");
    }

    @ModelAttribute
    public UserSkill getUserSkill() {
        return new UserSkill();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<UserSkill> userSkills = this.userSkillService.findPaginated(pageRequestDto);
        model.addAttribute("userSkills", userSkills);

        return "dashboard/ums/user-skill/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        List<User> users = this.userService.findAll();
        List<Skill> skills = this.skillService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("skills", skills);

        return "dashboard/ums/user-skill/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute UserSkill userSkill, BindingResult result, @RequestParam("user_id") Long userId, @RequestParam("skill_id") Long skillId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userSkill", userSkill).bind(result);
            return "redirect:/dashboard/ums/user-skill/create";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        Skill skill = this.skillService.findById(skillId);

        if (skill == null) {
            new NotifierHelper(attributes).message("Skill not found.").error();
        }

        try {
            userSkill.setUser(user);
            userSkill.setSkill(skill);
            this.userSkillService.save(userSkill);
            new NotifierHelper(attributes).message("User skill added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("User skill can not be added.").error();
            return "redirect:/dashboard/ums/user-skill/create";
        }

        return "redirect:/dashboard/ums/user-skill";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        UserSkill userSkill = this.userSkillService.findById(id);

        if (userSkill == null) {
            new NotifierHelper(attributes).message("User skill not found.").error();
            return "redirect:/dashboard/ums/user-skill";
        }

        model.addAttribute("userSkill", userSkill);

        return "dashboard/ums/user-skill/show";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {

        UserSkill userSkill = this.userSkillService.findById(id);

        if (userSkill == null) {
            new NotifierHelper(attributes).message("User skill not found.").error();
            return "redirect:/dashboard/ums/user-skill";
        }

        List<User> users = this.userService.findAll();
        List<Skill> skills = this.skillService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("skills", skills);

        model.addAttribute("userSkill", userSkill);

        return "dashboard/ums/user-skill/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute UserSkill userSkill, BindingResult result, @RequestParam("user_id") Long userId, @RequestParam("skill_id") Long skillId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userSkill", userSkill).bind(result);
            return "redirect:/dashboard/ums/user-skill/" + id + "/edit";
        }

        UserSkill updatableUserSkill = this.userSkillService.findById(id);

        if (updatableUserSkill == null) {
            new NotifierHelper(attributes).message("User skill not found.").error();
            return "redirect:/dashboard-skill/user";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        Skill skill = this.skillService.findById(skillId);

        if (skill == null) {
            new NotifierHelper(attributes).message("Skill not found.").error();
        }

        try {
            userSkill.setUser(user);
            userSkill.setSkill(skill);
            this.userSkillService.update(userSkill);
            new NotifierHelper(attributes).message("User skill updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User skill can not be updated.").error();
        }

        return "redirect:/dashboard/ums/user-skill/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        UserSkill userSkill = this.userSkillService.findById(id);

        if (userSkill == null) {
            new NotifierHelper(attributes).message("User skill not found.").error();
        }

        try {
            this.userSkillService.delete(userSkill);
            new NotifierHelper(attributes).message("User skill deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User skill can not ber deleted.").error();
        }

        return "redirect:/dashboard/ums/user-skill";
    }
}
