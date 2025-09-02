package com.pick.zick.service;

import com.pick.zick.peristence.request.SignupRequest;
import com.pick.zick.peristence.response.SignupResponse;
import com.pick.zick.peristence.request.LoginRequest;
import com.pick.zick.peristence.response.LoginResponse;
import com.pick.zick.domain.entity.User;
import com.pick.zick.domain.repository.UserRepository;
import com.pick.zick.config.JwtProvider;
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
                .userId(req.userId())
                .name(req.name())
                .password(passwordEncoder.encode(req.password()))
                .role(User.Role.STUDENT)
                .build();
        userRepository.save(user);

        // 4) JWT 발급
        String token = jwtProvider.generateToken(user.getUserId());

        // 5) 응답
        return SignupResponse.ok(user.getUserId());
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
        return LoginResponse.ok(user.getUserId(), token);
    }
}
