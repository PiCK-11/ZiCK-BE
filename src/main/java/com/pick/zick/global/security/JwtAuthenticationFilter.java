package com.pick.zick.global.security;

import com.pick.zick.global.security.userdetails.SecurityUserDetails;
import com.pick.zick.global.security.userdetails.SecurityUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;                 // 검증/파싱 담당 (네가 만든 클래스)
    private final SecurityUserDetailsService userDetailsService;  // DB 조회

    private static final String[] WHITE_LIST = {
            "/auth/login",
            "/auth/signup",
            "/zick/ping"
    };

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        final String path = request.getRequestURI();
        // CORS preflight는 인증 없이 통과
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) return true;

        for (String open : WHITE_LIST) {
            if (path.startsWith(open)) return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 이미 인증돼 있으면 패스 (체인 성능/중복 방지)
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = resolveToken(bearer);

        if (token != null && tokenProvider.validateToken(token)) {
            String userId = tokenProvider.getUserId(token);

            // DB에서 사용자 로드 → 권한 포함한 UserDetails
            SecurityUserDetails user = (SecurityUserDetails) userDetailsService.loadUserByUsername(userId);

            // Authentication 생성하여 컨텍스트에 세팅
            Authentication auth =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(String bearer) {
        if (bearer == null) return null;
        if (!bearer.startsWith("Bearer ")) return null;
        return bearer.substring(7);
    }
}
