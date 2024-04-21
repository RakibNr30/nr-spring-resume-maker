package com.resume.service.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.ums.UserAward;
import com.resume.helper.JpaSpecificationHelper;
import com.resume.repository.ums.UserAwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAwardService {

    private final UserAwardRepository userAwardRepository;

    @Autowired
    public UserAwardService(UserAwardRepository userAwardRepository) {
        this.userAwardRepository = userAwardRepository;
    }

    public List<UserAward> findAll() {
        return this.userAwardRepository.findAll();
    }

    public <T> List<UserAward> findAllBy(String propertyName, T value) {
        return null;
        //return this.userAwardRepository.findAllBy(propertyName, value);
    }

    public Page<UserAward> findPaginated(PageRequestDto pageRequestDto) {
        Specification<UserAward> specification = JpaSpecificationHelper.searchQuery(pageRequestDto.getSearch());
        return this.userAwardRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public UserAward save(UserAward userAward) {
        return this.userAwardRepository.save(userAward);
    }

    public UserAward findById(Long id) {
        return this.userAwardRepository.findById(id).orElse(null);
    }

    public UserAward update(UserAward userAward) {
        return this.userAwardRepository.save(userAward);
    }

    public void delete(UserAward userAward) {
        this.userAwardRepository.delete(userAward);
    }
}
