package com.resume.controller.dashboard.setting;

import com.resume.document.AppSetting;
import com.resume.helper.NotifierHelper;
import com.resume.helper.ValidationHelper;
import com.resume.service.setting.AppSettingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/setting")
public class SettingController {

    private final AppSettingService appSettingService;

    @Autowired
    public SettingController(AppSettingService appSettingService) {
        this.appSettingService = appSettingService;
    }

    @ModelAttribute
    public AppSetting getAppSetting() {
        return this.appSettingService.findOrSave(new AppSetting());
    }

    @RequestMapping("/app-setting")
    public String appSetting() {
        return "dashboard/setting/app-setting";
    }

    @RequestMapping(value = "/app-setting/update", method = RequestMethod.POST)
    public String updateAppSetting(@Valid @ModelAttribute AppSetting appSetting, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            new ValidationHelper(attributes).model("appSetting", appSetting).bind(result);
            return "redirect:/dashboard/setting/app-setting";
        }

        try {
            this.appSettingService.update(appSetting);
            new NotifierHelper(attributes).message("App setting updated successfully.").success();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            new NotifierHelper(attributes).message("App setting can not be updated.").error();
        }

        return "redirect:/dashboard/setting/app-setting";
    }
}
