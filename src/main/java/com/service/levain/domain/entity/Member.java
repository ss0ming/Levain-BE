package com.service.levain.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@RequiredArgsConstructor
public class Member {

    @Id
    @Column(name = "user_name", nullable = false, updatable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "reward", nullable = false)
    @ColumnDefault("0")
    private int reward;
}
