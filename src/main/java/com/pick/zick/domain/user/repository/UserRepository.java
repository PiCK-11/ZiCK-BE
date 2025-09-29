package com.pick.zick.domain.user.repository;

import com.pick.zick.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserId(String userId); // userId 기준으로 중복 체크
//    User findUserId(String userId);
    Optional<User> findByUserId(String userId);
}

