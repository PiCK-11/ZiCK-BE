package com.pick.zick.domain.user.persistence.repository;

import com.pick.zick.domain.user.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserId(String userId);
    Optional<User> findByUserId(String userId);
}

