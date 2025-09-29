package com.pick.zick.domain.user.domain;

import jakarta.persistence.*;
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
    private String userId; // PK

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String studentNumber;

    @Column(nullable = false)
    private Boolean applied;

    @Column(nullable = false)
    private String verified;

}

