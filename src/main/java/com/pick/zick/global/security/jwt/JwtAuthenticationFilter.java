package com.pick.zick.global.security.jwt;

import com.pick.zick.global.security.userdetails.SecurityUserDetails;
import com.pick.zick.global.security.userdetails.SecurityUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final SecurityUserDetailsService userDetailsService;
    private final TokenBlacklist tokenBlacklist;

    private static final String[] WHITE_LIST = {
            "/auth/login",
            "/auth/signup",
            "/zick/ping"
    };

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        final String path = request.getRequestURI();
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) return true; // CORS preflight
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

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = tokenProvider.resolveToken(request);

        if (token != null && tokenProvider.validateToken(token) && !tokenBlacklist.isBlacklisted(token)) {
            String userId = tokenProvider.getUserId(token);

            SecurityUserDetails user =
                    (SecurityUserDetails) userDetailsService.loadUserByUsername(userId);

            Authentication auth =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
