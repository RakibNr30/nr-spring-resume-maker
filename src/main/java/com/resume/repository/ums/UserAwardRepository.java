package com.resume.repository.ums;

import com.resume.entity.ums.UserAward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserAwardRepository extends JpaRepository<UserAward, Long>, JpaSpecificationExecutor<UserAward> {

}