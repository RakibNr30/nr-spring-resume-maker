package com.resume.service.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.ums.UserEducation;
import com.resume.helper.JpaSpecificationHelper;
import com.resume.repository.ums.UserEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEducationService {

    private final UserEducationRepository userEducationRepository;

    @Autowired
    public UserEducationService(UserEducationRepository userEducationRepository) {
        this.userEducationRepository = userEducationRepository;
    }

    public List<UserEducation> findAll() {
        return this.userEducationRepository.findAll();
    }

    public <T> List<UserEducation> findAllBy(String propertyName, T value) {
        return null;
        //return this.userEducationRepository.findAllBy(propertyName, value);
    }

    public Page<UserEducation> findPaginated(PageRequestDto pageRequestDto) {
        Specification<UserEducation> specification = JpaSpecificationHelper.searchQuery(pageRequestDto.getSearch());
        return this.userEducationRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public UserEducation save(UserEducation userEducation) {
        return this.userEducationRepository.save(userEducation);
    }

    public UserEducation findById(Long id) {
        return this.userEducationRepository.findById(id).orElse(null);
    }

    public UserEducation update(UserEducation userEducation) {
        return this.userEducationRepository.save(userEducation);
    }

    public void delete(UserEducation userEducation) {
        this.userEducationRepository.delete(userEducation);
    }
}
