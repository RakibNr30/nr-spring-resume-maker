package com.resume.service.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.ums.UserExperience;
import com.resume.helper.JpaSpecificationHelper;
import com.resume.repository.ums.UserExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExperienceService {

    private final UserExperienceRepository userExperienceRepository;

    @Autowired
    public UserExperienceService(UserExperienceRepository userExperienceRepository) {
        this.userExperienceRepository = userExperienceRepository;
    }

    public List<UserExperience> findAll() {
        return this.userExperienceRepository.findAll();
    }

    public <T> List<UserExperience> findAllBy(String propertyName, T value) {
        return null;
        //return this.userExperienceRepository.findAllBy(propertyName, value);
    }

    public Page<UserExperience> findPaginated(PageRequestDto pageRequestDto) {
        Specification<UserExperience> specification = JpaSpecificationHelper.searchQuery(pageRequestDto.getSearch());
        return this.userExperienceRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public UserExperience save(UserExperience userExperience) {
        return this.userExperienceRepository.save(userExperience);
    }

    public UserExperience findById(Long id) {
        return this.userExperienceRepository.findById(id).orElse(null);
    }

    public UserExperience update(UserExperience userExperience) {
        return this.userExperienceRepository.save(userExperience);
    }

    public void delete(UserExperience userExperience) {
        this.userExperienceRepository.delete(userExperience);
    }
}
