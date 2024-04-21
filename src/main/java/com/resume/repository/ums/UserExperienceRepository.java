package com.resume.repository.ums;

import com.resume.entity.ums.UserExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserExperienceRepository extends JpaRepository<UserExperience, Long>, JpaSpecificationExecutor<UserExperience> {

}