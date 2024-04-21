package com.resume.seeder;

import com.resume.entity.ums.Role;
import com.resume.entity.ums.User;
import com.resume.repository.ums.RoleRepository;
import com.resume.repository.ums.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserDataSeeder implements DataSeeder {
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedData() {
        this.seedAdminUser();
    }

    private void seedAdminUser() {
        List<Role> roles = this.roleRepository.findAllByName("ROLE_ADMIN");

        if (roles.isEmpty()) {
            throw new RuntimeException("Role(s) not found!");
        }

        if (this.userRepository.count() == 0) {
            User user = new User();
            user.setName("Mr Admin");
            user.setDob(LocalDate.of(1998, 8, 10));
            user.setUsername("admin");
            user.setEmail("admin@example.com");
            user.setMobile("0123456789");
            user.setPassword(this.passwordEncoder.encode("password"));
            user.setAddress("Bangladesh");
            user.setRoles(roles);

            this.userRepository.save(user);
        }
    }
}
