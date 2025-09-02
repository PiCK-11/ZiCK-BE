package com.pick.zick;

import com.pick.zick.global.security.userdetails.SecurityUserDetails;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret:비밀키 예시}")
    private String secretKey;

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUserId(String token) {
        Claims claims = (Claims) Jwts.parser()
                .setSigningKey(secretKey)
                .parse(token)
                .getBody();
        return claims.getSubject();
    }

    public Authentication buildAuthentication(SecurityUserDetails principal) {
        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
    }
}
