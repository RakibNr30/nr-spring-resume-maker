package com.resume.repository.setting;

import com.resume.document.AppSetting;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppSettingRepository extends MongoRepository<AppSetting, ObjectId> {
}
