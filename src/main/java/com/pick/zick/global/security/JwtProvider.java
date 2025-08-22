package com.pick.zick.global.security;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Component
public class JwtProvider {
    private final String secretKey = "비밀키예시"; // application.yml로 빼는 게 안전

    public String generateToken(String userId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + 1000L * 60 * 60); // 1시간

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}

