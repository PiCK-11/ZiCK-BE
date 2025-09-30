package com.pick.zick.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String studentNumber;

    @Column(nullable = false)
    private Boolean applied = false;

    @Column(nullable = false)
    private Boolean verified = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String loginId, String userName, String password, String studentNumber, Boolean applied, Boolean verified, String role) {
        this.loginId = loginId;
        this.userName = userName;
        this.password = password;
        this.studentNumber = studentNumber;
        this.role = Role.valueOf(role);
    }
}

