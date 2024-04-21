package com.resume.controller.dashboard.cms;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.Language;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.cms.LanguageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/dashboard/cms/language")
public class LanguageController {

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "Language");
    }

    @ModelAttribute
    public Language getLanguage() {
        return new Language();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<Language> languages = this.languageService.findPaginated(pageRequestDto);
        model.addAttribute("languages", languages);

        return "dashboard/cms/language/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "dashboard/cms/language/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute Language language, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("language", language).bind(result);
            return "redirect:/dashboard/cms/language/create";
        }

        try {
            this.languageService.save(language);
            new NotifierHelper(attributes).message("Language added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("Language can not be added.").error();
            return "redirect:/dashboard/cms/language/create";
        }

        return "redirect:/dashboard/cms/language";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        Language language = this.languageService.findById(id);

        if (language == null) {
            new NotifierHelper(attributes).message("Language not found.").error();
            return "redirect:/dashboard/cms/language";
        }

        model.addAttribute("language", language);

        return "dashboard/cms/language/show";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {

        Language language = this.languageService.findById(id);

        if (language == null) {
            new NotifierHelper(attributes).message("Language not found.").error();
            return "redirect:/dashboard/cms/language";
        }

        model.addAttribute("language", language);

        return "dashboard/cms/language/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute Language language, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("language", language).bind(result);
            return "redirect:/dashboard/cms/language" + id + "/edit";
        }

        Language updatableLanguage = this.languageService.findById(id);

        if (updatableLanguage == null) {
            new NotifierHelper(attributes).message("Language not found.").error();
            return "redirect:/dashboard/cms/language";
        }

        try {
            this.languageService.update(language);
            new NotifierHelper(attributes).message("Language updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("Language can not be updated.").error();
        }

        return "redirect:/dashboard/cms/language/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        Language language = this.languageService.findById(id);

        if (language == null) {
            new NotifierHelper(attributes).message("Language not found.").error();
        }

        try {
            this.languageService.delete(language);
            new NotifierHelper(attributes).message("Language deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("Language can not be deleted.").error();
        }

        return "redirect:/dashboard/cms/language";
    }
}
