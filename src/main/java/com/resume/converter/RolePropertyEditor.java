package com.resume.converter;

import com.resume.service.ums.RoleService;

import java.beans.PropertyEditorSupport;

public class RolePropertyEditor extends PropertyEditorSupport {

    private final RoleService roleService;

    public RolePropertyEditor(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(this.roleService.findById(Long.parseLong(text)));
    }
}
