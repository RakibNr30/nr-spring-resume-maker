package com.resume.repository.ums;

import com.resume.entity.ums.UserSocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserSocialAccountRepository extends JpaRepository<UserSocialAccount, Long>, JpaSpecificationExecutor<UserSocialAccount> {

}