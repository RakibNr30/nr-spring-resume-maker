package com.resume.service.ums;

import com.resume.dto.PageRequestDto;
import com.resume.entity.ums.Role;
import com.resume.helper.JpaSpecificationHelper;
import com.resume.repository.ums.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    public Page<Role> findPaginated(PageRequestDto pageRequestDto) {
        Specification<Role> specification = JpaSpecificationHelper.searchQuery(pageRequestDto.getSearch());
        return this.roleRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    public Role findById(Long id) {
        return this.roleRepository.findById(id).orElse(null);
    }

    public Role update(Role role) {
        return this.roleRepository.save(role);
    }

    public void delete(Role role) {
        this.roleRepository.delete(role);
    }
}
