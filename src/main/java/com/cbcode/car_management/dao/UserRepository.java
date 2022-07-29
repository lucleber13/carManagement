package com.cbcode.car_management.dao;

import com.cbcode.car_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUserName(String userName);
    public boolean existsByEmail(String email);
    public boolean existsByUserName(String userName);
}
