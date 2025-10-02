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

    public void updateVerified(Boolean verified) {
        this.verified = verified;
    }
}

