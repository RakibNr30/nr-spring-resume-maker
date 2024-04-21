package com.resume.advice;

import com.resume.document.AppSetting;
import com.resume.service.setting.AppSettingService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SettingAdvice {

    private final AppSettingService appSettingService;

    public SettingAdvice(AppSettingService appSettingService) {
        this.appSettingService = appSettingService;
    }

    @ModelAttribute
    public void globalAppSettingAttributes(Model model) {
        model.addAttribute("globalAppSetting", this.appSettingService.findOrSave(new AppSetting()));
    }
}
