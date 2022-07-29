package com.cbcode.car_management.dao;

import com.cbcode.car_management.model.Role;
import com.cbcode.car_management.model.Roles;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository {
    Optional<Role> findByRoleName(Roles role);
}
