package com.resume.repository.ums;

import com.resume.entity.ums.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long>, JpaSpecificationExecutor<UserInterest> {

}