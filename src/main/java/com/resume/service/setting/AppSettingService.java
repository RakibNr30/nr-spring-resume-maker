package com.resume.service.setting;

import com.resume.document.AppSetting;
import com.resume.repository.setting.AppSettingRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppSettingService {

    private final AppSettingRepository appSettingRepository;

    @Autowired
    public AppSettingService(AppSettingRepository appSettingRepository) {
        this.appSettingRepository = appSettingRepository;
    }

    public List<AppSetting> findAll() {
        return this.appSettingRepository.findAll();
    }

    public AppSetting save(AppSetting appSetting) {
        appSetting.setCreatedAt(LocalDateTime.now());
        return this.appSettingRepository.save(appSetting);
    }

    public AppSetting findById(ObjectId id) {
        return this.appSettingRepository.findById(id).orElse(null);
    }

    public AppSetting update(AppSetting appSetting) {
        appSetting.setUpdatedAt(LocalDateTime.now());
        return this.appSettingRepository.save(appSetting);
    }

    public void delete(AppSetting appSetting) {
        this.appSettingRepository.delete(appSetting);
    }

    public AppSetting findOrSave(AppSetting appSetting) {
        AppSetting firstAppSetting = this.appSettingRepository
                .findAll()
                .stream()
                .findFirst()
                .orElse(null);

        if (firstAppSetting == null) {
            appSetting.setCreatedAt(LocalDateTime.now());
            this.appSettingRepository.save(appSetting);
        }

        return firstAppSetting;
    }
}
