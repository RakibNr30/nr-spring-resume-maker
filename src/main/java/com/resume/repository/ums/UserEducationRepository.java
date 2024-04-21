package com.resume.repository.ums;

import com.resume.entity.ums.UserEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserEducationRepository extends JpaRepository<UserEducation, Long>, JpaSpecificationExecutor<UserEducation> {

}