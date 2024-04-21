package com.resume.controller.dashboard.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.Interest;
import com.resume.entity.ums.User;
import com.resume.entity.ums.UserInterest;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.cms.InterestService;
import com.resume.service.ums.UserInterestService;
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
@RequestMapping("/dashboard/ums/user-interest")
public class UserInterestController {

    private final UserService userService;
    
    private final InterestService interestService;

    private final UserInterestService userInterestService;

    @Autowired
    public UserInterestController(UserService userService, InterestService interestService, UserInterestService userInterestService) {
        this.userService = userService;
        this.interestService = interestService;
        this.userInterestService = userInterestService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "User Interest");
    }

    @ModelAttribute
    public UserInterest getUserInterest() {
        return new UserInterest();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<UserInterest> userInterests = this.userInterestService.findPaginated(pageRequestDto);
        model.addAttribute("userInterests", userInterests);

        return "dashboard/ums/user-interest/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        List<User> users = this.userService.findAll();
        List<Interest> interests = this.interestService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("interests", interests);

        return "dashboard/ums/user-interest/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute UserInterest userInterest, BindingResult result, @RequestParam("user_id") Long userId, @RequestParam("interest_id") Long interestId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userInterest", userInterest).bind(result);
            return "redirect:/dashboard/ums/user-interest/create";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        Interest interest = this.interestService.findById(interestId);

        if (interest == null) {
            new NotifierHelper(attributes).message("Interest not found.").error();
        }

        try {
            userInterest.setUser(user);
            userInterest.setInterest(interest);
            this.userInterestService.save(userInterest);
            new NotifierHelper(attributes).message("User interest added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("User interest can not be added.").error();
            return "redirect:/dashboard/ums/user-interest/create";
        }

        return "redirect:/dashboard/ums/user-interest";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        UserInterest userInterest = this.userInterestService.findById(id);

        if (userInterest == null) {
            new NotifierHelper(attributes).message("User interest not found.").error();
            return "redirect:/dashboard/ums/user-interest";
        }

        model.addAttribute("userInterest", userInterest);

        return "dashboard/ums/user-interest/show";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {

        UserInterest userInterest = this.userInterestService.findById(id);

        if (userInterest == null) {
            new NotifierHelper(attributes).message("User interest not found.").error();
            return "redirect:/dashboard/ums/user-interest";
        }

        List<User> users = this.userService.findAll();
        List<Interest> interests = this.interestService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("interests", interests);

        model.addAttribute("userInterest", userInterest);

        return "dashboard/ums/user-interest/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute UserInterest userInterest, BindingResult result, @RequestParam("user_id") Long userId, @RequestParam("interest_id") Long interestId, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("userInterest", userInterest).bind(result);
            return "redirect:/dashboard/ums/user-interest/" + id + "/edit";
        }

        UserInterest updatableUserInterest = this.userInterestService.findById(id);

        if (updatableUserInterest == null) {
            new NotifierHelper(attributes).message("User interest not found.").error();
            return "redirect:/dashboard-interest/user";
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            new NotifierHelper(attributes).message("User not found.").error();
        }

        Interest interest = this.interestService.findById(interestId);

        if (interest == null) {
            new NotifierHelper(attributes).message("Interest not found.").error();
        }

        try {
            userInterest.setUser(user);
            userInterest.setInterest(interest);
            this.userInterestService.update(userInterest);
            new NotifierHelper(attributes).message("User interest updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User interest can not be updated.").error();
        }

        return "redirect:/dashboard/ums/user-interest/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        UserInterest userInterest = this.userInterestService.findById(id);

        if (userInterest == null) {
            new NotifierHelper(attributes).message("User interest not found.").error();
        }

        try {
            this.userInterestService.delete(userInterest);
            new NotifierHelper(attributes).message("User interest deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("User interest can not ber deleted.").error();
        }

        return "redirect:/dashboard/ums/user-interest";
    }
}
