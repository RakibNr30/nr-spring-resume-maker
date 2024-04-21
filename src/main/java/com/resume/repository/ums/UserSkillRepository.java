package com.resume.repository.ums;

import com.resume.entity.ums.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long>, JpaSpecificationExecutor<UserSkill> {
}
