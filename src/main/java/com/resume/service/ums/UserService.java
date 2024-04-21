package com.resume.service.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.ums.User;
import com.resume.helper.JpaSpecificationHelper;
import com.resume.repository.ums.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Page<User> findPaginated(PageRequestDto pageRequestDto) {
        Specification<User> specification = JpaSpecificationHelper.searchQuery(pageRequestDto.getSearch());
        return this.userRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public List<User> findAllByRoleName(String roleName) {
        return this.userRepository.findAllByRoleName(roleName);
    }

    public Page<User> findPaginatedByRoleName(String roleName, PageRequestDto pageRequestDto) {
        Specification<User> specification = JpaSpecificationHelper
                .<User>searchQuery(pageRequestDto.getSearch())
                .and(JpaSpecificationHelper.hasRole("ROLE_USER"));

        return this.userRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User update(User user) {
        return this.userRepository.save(user);
    }

    public void delete(User user) {
        this.userRepository.delete(user);
    }
}
