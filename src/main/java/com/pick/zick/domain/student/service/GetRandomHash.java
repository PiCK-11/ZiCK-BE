package com.pick.zick.domain.student.service;

import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class GetRandomHash {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserFacade userFacade;

    public String makeHash() throws NoSuchAlgorithmException {
        User user = userFacade.getCurrentUser();
        Long studentId = user.getId();
        System.out.println(studentId);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String rawValue = studentId.toString() + timestamp;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(rawValue.getBytes(StandardCharsets.UTF_8));

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) hex.append(String.format("%02x", b));

        saveHashToRedis(hex.toString(),  studentId);
        return hex.toString();
    }

    private void saveHashToRedis(String hash, Long studentId) {
        //나중에 300L(5분) -> 30L(30초)로 변경할 것
        redisTemplate.opsForValue().set(hash, studentId.toString(), 300L, TimeUnit.SECONDS);
    }
}
