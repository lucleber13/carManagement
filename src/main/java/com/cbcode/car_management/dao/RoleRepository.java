package com.cbcode.car_management.dao;

import com.cbcode.car_management.model.Role;
import com.cbcode.car_management.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(Roles role);
}
