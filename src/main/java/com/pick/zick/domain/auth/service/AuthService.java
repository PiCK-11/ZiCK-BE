package com.pick.zick.domain.auth.service;

import com.pick.zick.domain.auth.dto.request.SignupRequest;
import com.pick.zick.domain.auth.dto.response.SignupResponse;
import com.pick.zick.domain.auth.dto.request.LoginRequest;
import com.pick.zick.domain.auth.dto.response.LoginResponse;
import com.pick.zick.domain.user.persistence.entity.User;
import com.pick.zick.domain.user.persistence.repository.UserRepository;
import com.pick.zick.global.security.jwt.JwtTokenProvider;
import com.pick.zick.global.security.jwt.TokenBlacklist;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;
    private final TokenBlacklist tokenBlacklist;

    /** 회원가입**/
    public SignupResponse signup(SignupRequest req) {
        // 1) 아이디 중복 검사
        if (userRepository.existsById(req.userId())) {
            throw new IllegalStateException("이미 가입된 아이디입니다");
        }

        // 2) 비밀번호 재확인
        if (!req.password().equals(req.confirmPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        //롤별 필드
        User.Role forcedRole = User.Role.STUDENT;
        if (req.role() != null && req.role() != User.Role.STUDENT) {
            throw new IllegalArgumentException("현재는 학생만 회원가입할 수 있습니다");
        } //cafeteria 가입 시도 시 400

        // 3) 저장
        User user = User.builder()
                .userId(req.userId())
                .userName(req.userName())
                .password(passwordEncoder.encode(req.password()))
                .role(forcedRole)
                .studentNumber(req.studentNumber())
                .verifield(false)
                .build();
        userRepository.save(user);

        // 4) JWT 발급
        String token = jwtProvider.generateToken(user.getUserId());

        // 5) 응답
        return SignupResponse.ok(user.getUserId(), token, forcedRole, user.isVerifield());
    }

    /** 로그인 **/
    public LoginResponse login(LoginRequest req) {
        // 1) 사용자 조회
        User user = userRepository.findById(req.userId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디입니다."));

        // 2) 비밀번호 검증
        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        // 3) JWT발급
        String token = jwtProvider.generateToken(user.getUserId());

        // 4) 응답
        return LoginResponse.ok(user.getUserId(), token, user.getRole());
    }

    public void logout(HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        if (token != null && jwtProvider.validateToken(token)) {
            long ttl = jwtProvider.getRemainingValidityMillis(token);
            tokenBlacklist.blacklist(token, ttl);
        }
    }
}
