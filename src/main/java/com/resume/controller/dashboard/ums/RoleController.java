package com.resume.controller.dashboard.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.ums.Role;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.ums.RoleService;
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
@RequestMapping("/dashboard/ums/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ModelAttribute
    public void common(Model model) {
        model.addAttribute("title", "Role");
    }

    @ModelAttribute
    public Role getRole() {
        return new Role();
    }

    @RequestMapping
    public String index(Model model, PageRequestDto pageRequestDto) {
        Page<Role> roles = this.roleService.findPaginated(pageRequestDto);
        model.addAttribute("roles", roles);

        return "dashboard/ums/role/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "dashboard/ums/role/create";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid @ModelAttribute Role role, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("role", role).bind(result);
            return "redirect:/dashboard/ums/role/create";
        }

        try {
            this.roleService.save(role);
            new NotifierHelper(attributes).message("Role added successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("Role can not be added.").error();
            return "redirect:/dashboard/ums/role/create";
        }

        return "redirect:/dashboard/ums/role";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        Role role = this.roleService.findById(id);

        if (role == null) {
            new NotifierHelper(attributes).message("Role not found.").error();
            return "redirect:/dashboard/ums/role";
        }

        model.addAttribute("role", role);

        return "dashboard/ums/role/show";
    }

    //@RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {

        Role role = this.roleService.findById(id);

        if (role == null) {
            new NotifierHelper(attributes).message("Role not found.").error();
            return "redirect:/dashboard/ums/role";
        }

        model.addAttribute("role", role);

        return "dashboard/ums/role/edit";
    }

    //@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute Role role, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("role", role).bind(result);
            return "redirect:/dashboard/ums/role/" + id + "/edit";
        }

        Role updatableRole = this.roleService.findById(id);

        if (updatableRole == null) {
            new NotifierHelper(attributes).message("Role not found.").error();
            return "redirect:/dashboard/ums/role";
        }

        try {
            this.roleService.update(role);
            new NotifierHelper(attributes).message("Role updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("Role can not be updated.").error();
        }

        return "redirect:/dashboard/ums/role/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/destroy", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes attributes) {

        Role role = this.roleService.findById(id);

        if (role == null) {
            new NotifierHelper(attributes).message("Role not found.").error();
        }

        try {
            this.roleService.delete(role);
            new NotifierHelper(attributes).message("Role deleted successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            new NotifierHelper(attributes).message("Role can not be deleted.").error();
        }

        return "redirect:/dashboard/ums/role";
    }
}
