package com.pick.zick.domain.auth.service;

import com.pick.zick.domain.auth.dto.SignupRequest;
import com.pick.zick.domain.auth.dto.SignupResponse;
import com.pick.zick.domain.auth.dto.LoginRequest;
import com.pick.zick.domain.auth.dto.LoginResponse;
import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.repository.UserRepository;
import com.pick.zick.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /** 회원가입**/
    public SignupResponse signup(SignupRequest req) {
        // 1) 아이디 중복
        if (userRepository.existsById(req.userId())) {
            throw new IllegalStateException("이미 가입된 아이디입니다");
        }

        // 2) 비밀번호 재확인
        if (!req.password().equals(req.confirmPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        // 3) 저장
        User user = User.builder()
                .loginId(req.userId())
                .name(req.name())
                .password(passwordEncoder.encode(req.password()))
                .build();
        userRepository.save(user);

        // 4) JWT 발급
        String token = jwtProvider.generateToken(user.getLoginId());

        // 5) 응답
        return SignupResponse.ok(user.getLoginId());
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
        String token = jwtProvider.generateToken(user.getLoginId());

        // 4) 응답
        return LoginResponse.ok(user.getLoginId(), token);
    }
}
