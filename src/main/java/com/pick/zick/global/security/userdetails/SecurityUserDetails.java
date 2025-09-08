package com.pick.zick.global.security.userdetails;

import com.pick.zick.domain.user.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class SecurityUserDetails implements UserDetails {

    private final String userId;
    private final String password;
    private final String name;
    private final String role;

    public static SecurityUserDetails from(User u) {
        if (u.getRole() == null) {
            throw new IllegalStateException("role이 비어있습니다: " + u.getUserId());
        }
        return new SecurityUserDetails(
                u.getUserId(),
                u.getPassword(),
                u.getUserName(),
                u.getRole().name()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null || role.isBlank()) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return userId; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
