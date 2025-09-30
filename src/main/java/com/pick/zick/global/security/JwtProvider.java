package com.pick.zick.global.security;

import com.pick.zick.global.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final AuthDetailsService authDetailsService;
    private final JwtProperty jwtProperty;

    private static final String ACCESS_TOKEN = "access_token";

    public TokenResponse generateToken(String id, String role) {
        String accessToken = generateToken(id, ACCESS_TOKEN, role, jwtProperty.getAccessExp());
        LocalDateTime accessExpiredAt = LocalDateTime.now().withNano(0).plusSeconds(jwtProperty.getAccessExp());

        return new TokenResponse(accessToken,jwtProperty.getPrefix(),"200" );
    }


    private String generateToken(String id, String type, String role, Long exp) {
        return Jwts.builder()
                .setSubject(id)
                .setHeaderParam("typ", type)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, jwtProperty.getJwtSecret())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();

    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(jwtProperty.getHeader());
        if (token != null && token.startsWith(jwtProperty.getPrefix()) && token.length() > jwtProperty.getPrefix().length() + 1) {
            return token.substring(jwtProperty.getPrefix().length() + 1);
        } else {
            return null;
        }
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperty.getJwtSecret())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication authentication(String token) {
        Claims body = getJws(token).getBody();

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperty.getJwtSecret()).parseClaimsJws(token);
        } catch (Exception e) {
            throw e;
        }
    }

    private UserDetails getDetails(Claims body) {
        return authDetailsService.loadUserByUsername(body.getSubject());
    }
}