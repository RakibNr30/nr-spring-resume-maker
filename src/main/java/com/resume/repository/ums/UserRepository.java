package com.resume.repository.ums;

import com.resume.entity.ums.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value =
            "SELECT u.* FROM users u " +
            "JOIN users_roles ur ON ur.user_id = u.id " +
            "JOIN roles r ON r.id = ur.role_id " +
            "WHERE r.name = :roleName", nativeQuery = true)
    List<User> findAllByRoleName(@Param("roleName") String roleName);

    User findByUsername(String username);
}