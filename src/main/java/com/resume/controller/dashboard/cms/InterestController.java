package com.resume.controller.dashboard.cms;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.Interest;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.cms.InterestService;
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
@RequestMapping("/dashboard/cms/interest")
public class InterestController {

    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "Interest");
    }

    @ModelAttribute
    public Interest getInterest() {
        return new Interest();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<Interest> interests = this.interestService.findPaginated(pageRequestDto);
        model.addAttribute("interests", interests);

        return "dashboard/cms/interest/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "dashboard/cms/interest/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute Interest interest, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("interest", interest).bind(result);
            return "redirect:/dashboard/cms/interest/create";
        }

        try {
            this.interestService.save(interest);
            new NotifierHelper(attributes).message("Interest added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("Interest can not be added.").error();
            return "redirect:/dashboard/cms/interest/create";
        }

        return "redirect:/dashboard/cms/interest";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        Interest interest = this.interestService.findById(id);

        if (interest == null) {
            new NotifierHelper(attributes).message("Interest not found.").error();
            return "redirect:/dashboard/cms/interest";
        }

        model.addAttribute("interest", interest);

        return "dashboard/cms/interest/show";
    }

    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {

        Interest interest = this.interestService.findById(id);

        if (interest == null) {
            new NotifierHelper(attributes).message("Interest not found.").error();
            return "redirect:/dashboard/cms/interest";
        }

        model.addAttribute("interest", interest);

        return "dashboard/cms/interest/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute Interest interest, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("interest", interest).bind(result);
            return "redirect:/dashboard/cms/interest/" + id + "/edit";
        }

        Interest updatableInterest = this.interestService.findById(id);

        if (updatableInterest == null) {
            new NotifierHelper(attributes).message("Interest not found.").error();
            return "redirect:/dashboard/cms/interest";
        }

        try {
            this.interestService.update(interest);
            new NotifierHelper(attributes).message("Interest updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("Interest can not be updated.").error();
        }

        return "redirect:/dashboard/cms/interest/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        Interest interest = this.interestService.findById(id);

        if (interest == null) {
            new NotifierHelper(attributes).message("Interest not found.").error();
        }

        try {
            this.interestService.delete(interest);
            new NotifierHelper(attributes).message("Interest deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("Interest can not be deleted.").error();
        }

        return "redirect:/dashboard/cms/interest";
    }
}
