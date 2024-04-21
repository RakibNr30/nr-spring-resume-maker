package com.resume.repository.cms;

import com.resume.entity.cms.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long>, JpaSpecificationExecutor<SocialAccount> {

}
