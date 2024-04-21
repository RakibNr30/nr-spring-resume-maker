package com.resume.controller.dashboard.cms;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.SocialAccount;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.cms.SocialAccountService;
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
@RequestMapping("/dashboard/cms/social-account")
public class SocialAccountController {

    private final SocialAccountService socialAccountService;

    @Autowired
    public SocialAccountController(SocialAccountService socialAccountService) {
        this.socialAccountService = socialAccountService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "Social Account");
    }

    @ModelAttribute
    public SocialAccount getSocialAccount() {
        return new SocialAccount();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<SocialAccount> socialAccounts = this.socialAccountService.findPaginated(pageRequestDto);
        model.addAttribute("socialAccounts", socialAccounts);

        return "dashboard/cms/social-account/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "dashboard/cms/social-account/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute SocialAccount socialAccount, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("socialAccount", socialAccount).bind(result);
            return "redirect:/dashboard/cms/social-account/create";
        }

        try {
            this.socialAccountService.save(socialAccount);
            new NotifierHelper(attributes).message("Social account added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("Social account can not be added.").error();
            return "redirect:/dashboard/cms/social-account/create";
        }

        return "redirect:/dashboard/cms/social-account";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        SocialAccount socialAccount = this.socialAccountService.findById(id);

        if (socialAccount == null) {
            new NotifierHelper(attributes).message("Social account not found.").error();
            return "redirect:/dashboard/cms/social-account";
        }

        model.addAttribute("socialAccount", socialAccount);

        return "dashboard/cms/social-account/show";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {

        SocialAccount socialAccount = this.socialAccountService.findById(id);

        if (socialAccount == null) {
            new NotifierHelper(attributes).message("Social account not found.").error();
            return "redirect:/dashboard/cms/social-account";
        }

        model.addAttribute("socialAccount", socialAccount);

        return "dashboard/cms/social-account/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute SocialAccount socialAccount, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("socialAccount", socialAccount).bind(result);
            return "redirect:/dashboard/cms/social-account/" + id + "/edit";
        }

        SocialAccount updatableSocialAccount = this.socialAccountService.findById(id);

        if (updatableSocialAccount == null) {
            new NotifierHelper(attributes).message("Social account not found.").error();
            return "redirect:/dashboard/cms/social-account";
        }

        try {
            this.socialAccountService.update(socialAccount);
            new NotifierHelper(attributes).message("Social account updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("Social account can not be updated.").error();
        }

        return "redirect:/dashboard/cms/social-account/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        SocialAccount socialAccount = this.socialAccountService.findById(id);

        if (socialAccount == null) {
            new NotifierHelper(attributes).message("Social account not found.").error();
        }

        try {
            this.socialAccountService.delete(socialAccount);
            new NotifierHelper(attributes).message("Social account deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("Social account can not be deleted.").error();
        }

        return "redirect:/dashboard/cms/social-account";
    }
}
