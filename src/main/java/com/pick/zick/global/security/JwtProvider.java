package com.pick.zick.global.security;

import com.pick.zick.global.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final AuthDetailsService authDetailsService;
    private final JwtProperty jwtProperty;
    private SecretKey secretKey;

    private static final String ACCESS_TOKEN = "access_token";

    /** SecretKey 초기화 **/
    @PostConstruct
    public void init() {
        // Base64 인코딩된 문자열을 SecretKey로 변환
        byte[] keyBytes = Base64.getEncoder().encode(jwtProperty.getJwtSecret().getBytes());
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /** JWT 발급 **/
    public TokenResponse generateToken(String id, String role) {
        String accessToken = generateToken(id, ACCESS_TOKEN, role, jwtProperty.getAccessExp());
        return new TokenResponse(accessToken, jwtProperty.getPrefix(), "200");
    }

    private String generateToken(String id, String type, String role, Long exp) {
        return Jwts.builder()
                .setSubject(id)
                .setHeaderParam("typ", type)
                .claim("role", role)
                .signWith(secretKey) // <- deprecated 아님
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    /** 요청에서 토큰 추출 **/
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(jwtProperty.getHeader());
        if (token != null && token.startsWith(jwtProperty.getPrefix())) {
            return token.substring(jwtProperty.getPrefix().length()).trim();
        }
        return null;
    }

    /** 토큰 유효성 검사 **/
    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Authentication 생성 **/
    public Authentication authentication(String token) {
        Claims body = getJws(token).getBody();
        UserDetails userDetails = authDetailsService.loadUserByUsername(body.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Jws<Claims> getJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}