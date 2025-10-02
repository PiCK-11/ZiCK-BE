package com.pick.zick.domain.user.repository;

import com.pick.zick.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByloginId(String loginId); // userId 기준으로 중복 체크
//    User findUserId(String loginId);
    Optional<User> findByloginId(String loginId);

    String loginId(String loginId);
}

