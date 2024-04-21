package com.resume.service.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.ums.UserSkill;
import com.resume.helper.JpaSpecificationHelper;
import com.resume.repository.ums.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkillService {

    private final UserSkillRepository userSkillRepository;

    @Autowired
    public UserSkillService(UserSkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    public List<UserSkill> findAll() {
        return this.userSkillRepository.findAll();
    }

    public <T> List<UserSkill> findAllBy(String propertyName, T value) {
        return null;
        //return this.userSkillRepository.findAllBy(propertyName, value);
    }

    public Page<UserSkill> findPaginated(PageRequestDto pageRequestDto) {
        Specification<UserSkill> specification = JpaSpecificationHelper.searchQuery(pageRequestDto.getSearch());
        return this.userSkillRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public UserSkill save(UserSkill userSkill) {
        return this.userSkillRepository.save(userSkill);
    }

    public UserSkill findById(Long id) {
        return this.userSkillRepository.findById(id).orElse(null);
    }

    public UserSkill update(UserSkill userSkill) {
        return this.userSkillRepository.save(userSkill);
    }

    public void delete(UserSkill userSkill) {
        this.userSkillRepository.delete(userSkill);
    }
}
