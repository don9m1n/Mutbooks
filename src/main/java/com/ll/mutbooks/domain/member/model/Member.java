package com.ll.mutbooks.domain.member.model;

import com.ll.mutbooks.common.auditing.BaseEntity;
import com.ll.mutbooks.domain.member.dto.JoinForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private AuthLevel authLevel;

    @Builder
    private Member(String username, String password, String email, String nickname, AuthLevel authLevel) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.authLevel = authLevel;
    }

    public static Member of(JoinForm form) {
        return Member.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .email(form.getEmail())
                .authLevel(AuthLevel.NORMAL)
                .build();
    }
}
