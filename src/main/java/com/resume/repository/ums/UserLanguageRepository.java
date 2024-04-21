package com.resume.repository.ums;

import com.resume.entity.ums.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long>, JpaSpecificationExecutor<UserLanguage> {

}