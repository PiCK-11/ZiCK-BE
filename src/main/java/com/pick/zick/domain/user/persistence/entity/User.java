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
    private String name;
    private String password;
    private Integer studentNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        STUDENT, CAFETERIA
    }

    public void updateProfile(String name, Integer studentNumber) {
        if (name != null && !name.isBlank()) this.name = name;
        if (studentNumber != null) this.studentNumber = studentNumber;
    }
}

