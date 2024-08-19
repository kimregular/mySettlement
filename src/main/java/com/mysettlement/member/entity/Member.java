package com.mysettlement.member.entity;

import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    private MemberRole role;

    public Member update(String name) {
        this.name = name;
        return this;
    }

    @Builder
    private Member(String name, String email, MemberRole role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public static Member of(String name, String email, MemberRole role) {
        return builder()
                .name(name)
                .email(email)
                .role(role)
                .build();
    }
}
