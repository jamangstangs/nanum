package com.nanum.market.repository;


import com.nanum.market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    User findUsersByUsername(String userEmail);
    User findByUsername(String sender);

}