package com.resume.converter;

import com.resume.service.cms.SkillService;
import java.beans.PropertyEditorSupport;

public class SkillPropertyEditor extends PropertyEditorSupport {

    private final SkillService skillService;

    public SkillPropertyEditor(SkillService skillService) {
        this.skillService = skillService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(this.skillService.findById(Long.parseLong(text)));
    }
}
