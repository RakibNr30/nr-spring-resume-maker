package com.resume.repository.cms;

import com.resume.entity.cms.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InterestRepository extends JpaRepository<Interest, Long>, JpaSpecificationExecutor<Interest> {

}
