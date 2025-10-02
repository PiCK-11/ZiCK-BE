package com.pick.zick.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String loginId;   // PK
    private String userName;
    private String password;
    private Integer studentNumber;

    @Builder.Default
    private boolean verified = false;

    public void updateProfile(String name, String password) {
        if (name != null && !name.isBlank()) {
            this.userName = name;
        }
        if (password != null && !password.isBlank()) {
            this.password = password;
        }
    }
}

