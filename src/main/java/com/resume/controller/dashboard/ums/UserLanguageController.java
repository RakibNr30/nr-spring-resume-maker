package com.resume.controller.dashboard.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.Language;
import com.resume.entity.ums.User;
import com.resume.entity.ums.UserLanguage;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.cms.LanguageService;
import com.resume.service.ums.UserLanguageService;
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
@RequestMapping("/dashboard/ums/user-language")
public class UserLanguageController {

    private final UserService userService;

    private final LanguageService languageService;

    private final UserLanguageService userLanguageService;

    @Autowired
    public UserLanguageController(UserService userService, LanguageService languageService, UserLanguageService userLanguageService) {
        this.userService = userService;
        this.languageService = languageService;
        this.userLanguageService = userLanguageService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "User Language");
    }

    @ModelAttribute
    public UserLanguage getUserLanguage() {
        return new UserLanguage();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<UserLanguage> userLanguages = this.userLanguageService.findPaginated(pageRequestDto);
        model.addAttribute("userLanguages", userLanguages);

        return "dashboard/ums/user-language/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        List<User> users = this.userService.findAll();
        List<Language> languages = this.languageService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("languages", languages);

        return "dashboard/ums/user-language/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute UserLanguage userLanguage, BindingResult result, @RequestParam("user_id") Long userId, @RequestParam("language_id") Long languageId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userLanguage", userLanguage).bind(result);
            return "redirect:/dashboard/ums/user-language/create";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        Language language = this.languageService.findById(languageId);

        if (language == null) {
            new NotifierHelper(attributes).message("Language not found.").error();
        }

        try {
            userLanguage.setUser(user);
            userLanguage.setLanguage(language);
            this.userLanguageService.save(userLanguage);
            new NotifierHelper(attributes).message("User language added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("User language can not be added.").error();
            return "redirect:/dashboard/ums/user-language/create";
        }

        return "redirect:/dashboard/ums/user-language";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        UserLanguage userLanguage = this.userLanguageService.findById(id);

        if (userLanguage == null) {
            new NotifierHelper(attributes).message("User language not found.").error();
            return "redirect:/dashboard/ums/user-language";
        }

        model.addAttribute("userLanguage", userLanguage);

        return "dashboard/ums/user-language/show";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {

        UserLanguage userLanguage = this.userLanguageService.findById(id);

        if (userLanguage == null) {
            new NotifierHelper(attributes).message("User language not found.").error();
            return "redirect:/dashboard/ums/user-language";
        }

        List<User> users = this.userService.findAll();
        List<Language> languages = this.languageService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("languages", languages);

        model.addAttribute("userLanguage", userLanguage);

        return "dashboard/ums/user-language/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute UserLanguage userLanguage, BindingResult result, @RequestParam("user_id") Long userId, @RequestParam("language_id") Long languageId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userLanguage", userLanguage).bind(result);
            return "redirect:/dashboard/ums/user-language/" + id + "/edit";
        }

        UserLanguage updatableUserLanguage = this.userLanguageService.findById(id);

        if (updatableUserLanguage == null) {
            new NotifierHelper(attributes).message("User language not found.").error();
            return "redirect:/dashboard-language/user";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        Language language = this.languageService.findById(languageId);

        if (language == null) {
            new NotifierHelper(attributes).message("Language not found.").error();
        }

        try {
            userLanguage.setUser(user);
            userLanguage.setLanguage(language);
            this.userLanguageService.update(userLanguage);
            new NotifierHelper(attributes).message("User language updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User language can not be updated.").error();
        }

        return "redirect:/dashboard/ums/user-language/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        UserLanguage userLanguage = this.userLanguageService.findById(id);

        if (userLanguage == null) {
            new NotifierHelper(attributes).message("User language not found.").error();
        }

        try {
            this.userLanguageService.delete(userLanguage);
            new NotifierHelper(attributes).message("User language deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User language can not ber deleted.").error();
        }

        return "redirect:/dashboard/ums/user-language";
    }
}
