package com.pick.zick.domain.auth.service;

import com.pick.zick.domain.auth.dto.SignupRequest;
import com.pick.zick.domain.auth.dto.LoginRequest;
import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.repository.UserRepository;
import com.pick.zick.global.security.JwtProvider;
import com.pick.zick.global.security.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**회원가입**/
    public TokenResponse signup(SignupRequest req) {
        // 1) 아이디 중복
        if (userRepository.existsByLoginId(req.loginId())) {
            throw new IllegalStateException("이미 가입된 아이디입니다");
        }


        // 3) 저장
        User user = User.builder()
                .loginId(req.loginId())
                .userName(req.userName())
                .studentNumber(req.studentNumber())
                .role(req.role())
                .password(passwordEncoder.encode(req.password()))
                .build();
        userRepository.save(user);

        // 5) 응답
        return jwtProvider.generateToken(user.getLoginId(), user.getRole().toString());
    }

    /** 로그인 **/
    public TokenResponse login(LoginRequest req) {
        // 1) 사용자 조회
        User user = userRepository.findByLoginId(req.loginId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디입니다."));

        // 2) 비밀번호 검증
        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            System.out.println(req.password());
            System.out.println(user.getPassword());
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return jwtProvider.generateToken(user.getLoginId(), user.getRole().toString());
    }
}
