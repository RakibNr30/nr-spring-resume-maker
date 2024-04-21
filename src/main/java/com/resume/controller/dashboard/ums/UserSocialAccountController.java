package com.resume.controller.dashboard.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.SocialAccount;
import com.resume.entity.ums.User;
import com.resume.entity.ums.UserSocialAccount;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.cms.SocialAccountService;
import com.resume.service.ums.UserSocialAccountService;
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
@RequestMapping("/dashboard/ums/user-social-account")
public class UserSocialAccountController {

    private final UserService userService;

    private final SocialAccountService socialAccountService;

    private final UserSocialAccountService userSocialAccountService;

    @Autowired
    public UserSocialAccountController(UserService userService, SocialAccountService socialAccountService, UserSocialAccountService userSocialAccountService) {
        this.userService = userService;
        this.socialAccountService = socialAccountService;
        this.userSocialAccountService = userSocialAccountService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "User Social Account");
    }

    @ModelAttribute
    public UserSocialAccount getUserSocialAccount() {
        return new UserSocialAccount();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<UserSocialAccount> userSocialAccounts = this.userSocialAccountService.findPaginated(pageRequestDto);
        model.addAttribute("userSocialAccounts", userSocialAccounts);

        return "dashboard/ums/user-social-account/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        List<User> users = this.userService.findAll();
        List<SocialAccount> socialAccounts = this.socialAccountService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("socialAccounts", socialAccounts);

        return "dashboard/ums/user-social-account/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute UserSocialAccount userSocialAccount, BindingResult result, @RequestParam("user_id") Long userId, @RequestParam("social_account_id") Long socialAccountId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userSocialAccount", userSocialAccount).bind(result);
            return "redirect:/dashboard/ums/user-social-account/create";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        SocialAccount socialAccount = this.socialAccountService.findById(socialAccountId);

        if (socialAccount == null) {
            new NotifierHelper(attributes).message("Social account not found.").error();
        }

        try {
            userSocialAccount.setUser(user);
            userSocialAccount.setSocialAccount(socialAccount);
            this.userSocialAccountService.save(userSocialAccount);
            new NotifierHelper(attributes).message("User social account added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("User social account can not be added.").error();
            return "redirect:/dashboard/ums/user-social-account/create";
        }

        return "redirect:/dashboard/ums/user-social-account";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        UserSocialAccount userSocialAccount = this.userSocialAccountService.findById(id);

        if (userSocialAccount == null) {
            new NotifierHelper(attributes).message("User social account not found.").error();
            return "redirect:/dashboard/ums/user-socialAccount";
        }

        model.addAttribute("userSocialAccount", userSocialAccount);

        return "dashboard/ums/user-social-account/show";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {

        UserSocialAccount userSocialAccount = this.userSocialAccountService.findById(id);

        if (userSocialAccount == null) {
            new NotifierHelper(attributes).message("User social account not found.").error();
            return "redirect:/dashboard/ums/user-social-account";
        }

        List<User> users = this.userService.findAll();
        List<SocialAccount> socialAccounts = this.socialAccountService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("socialAccounts", socialAccounts);

        model.addAttribute("userSocialAccount", userSocialAccount);

        return "dashboard/ums/user-social-account/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute UserSocialAccount userSocialAccount, BindingResult result, @RequestParam("user_id") Long userId, @RequestParam("social_account_id") Long socialAccountId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userSocialAccount", userSocialAccount).bind(result);
            return "redirect:/dashboard/ums/user-social-account/" + id + "/edit";
        }

        UserSocialAccount updatableUserSocialAccount = this.userSocialAccountService.findById(id);

        if (updatableUserSocialAccount == null) {
            new NotifierHelper(attributes).message("User social account not found.").error();
            return "redirect:/dashboard-social-account/user";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        SocialAccount socialAccount = this.socialAccountService.findById(socialAccountId);

        if (socialAccount == null) {
            new NotifierHelper(attributes).message("Social account not found.").error();
        }

        try {
            userSocialAccount.setUser(user);
            userSocialAccount.setSocialAccount(socialAccount);
            this.userSocialAccountService.update(userSocialAccount);
            new NotifierHelper(attributes).message("User social account updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User social account can not be updated.").error();
        }

        return "redirect:/dashboard/ums/user-social-account/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        UserSocialAccount userSocialAccount = this.userSocialAccountService.findById(id);

        if (userSocialAccount == null) {
            new NotifierHelper(attributes).message("User social account not found.").error();
        }

        try {
            this.userSocialAccountService.delete(userSocialAccount);
            new NotifierHelper(attributes).message("User social account deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User social account can not ber deleted.").error();
        }

        return "redirect:/dashboard/ums/user-social-account";
    }
}
