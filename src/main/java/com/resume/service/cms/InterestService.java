package com.resume.service.cms;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.Interest;
import com.resume.helper.JpaSpecificationHelper;
import com.resume.repository.cms.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestService {

    private final InterestRepository interestRepository;

    @Autowired
    public InterestService(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public List<Interest> findAll() {
        return this.interestRepository.findAll();
    }

    public Page<Interest> findPaginated(PageRequestDto pageRequestDto) {
        Specification<Interest> specification = JpaSpecificationHelper.searchQuery(pageRequestDto.getSearch());
        return this.interestRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public Interest save(Interest interest) {
        return this.interestRepository.save(interest);
    }

    public Interest findById(Long id) {
        return this.interestRepository.findById(id).orElse(null);
    }

    public Interest update(Interest interest) {
        return this.interestRepository.save(interest);
    }

    public void delete(Interest interest) {
        this.interestRepository.delete(interest);
    }
}
