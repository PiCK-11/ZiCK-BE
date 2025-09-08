package com.pick.zick.domain.user.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private String userId;   // PK
    private String userName;
    private String password;
    private Integer studentNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        STUDENT, CAFETERIA
    }

    public void updateProfile(String userName, Integer studentNumber) {
        if (userName != null && !userName.isBlank()) this.userName = userName;
        if (studentNumber != null) this.studentNumber = studentNumber;
    }
}

