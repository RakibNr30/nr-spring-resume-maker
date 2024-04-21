package com.resume.service.cms;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.SocialAccount;
import com.resume.helper.JpaSpecificationHelper;
import com.resume.repository.cms.SocialAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialAccountService {

    private final SocialAccountRepository socialAccountRepository;

    @Autowired
    public SocialAccountService(SocialAccountRepository socialAccountRepository) {
        this.socialAccountRepository = socialAccountRepository;
    }

    public List<SocialAccount> findAll() {
        return this.socialAccountRepository.findAll();
    }

    public Page<SocialAccount> findPaginated(PageRequestDto pageRequestDto) {
        Specification<SocialAccount> specification = JpaSpecificationHelper.searchQuery(pageRequestDto.getSearch());
        return this.socialAccountRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public SocialAccount save(SocialAccount socialAccount) {
        return this.socialAccountRepository.save(socialAccount);
    }

    public SocialAccount findById(Long id) {
        return this.socialAccountRepository.findById(id).orElse(null);
    }

    public SocialAccount update(SocialAccount socialAccount) {
        return this.socialAccountRepository.save(socialAccount);
    }

    public void delete(SocialAccount socialAccount) {
        this.socialAccountRepository.delete(socialAccount);
    }
}
