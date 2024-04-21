package com.resume.seeder;

import com.resume.entity.ums.Role;
import com.resume.repository.ums.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDataSeeder implements DataSeeder {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleDataSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedData() {
        this.seedRole();
    }

    private void seedRole() {
        if (this.roleRepository.count() == 0) {
            Role role = new Role();
            role.setName("ROLE_ADMIN");

            this.roleRepository.save(role);
        }
    }
}
