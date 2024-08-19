package com.mysettlement.member.entity;

import com.mysettlement.jwt.OAuth2ResponseDto;
import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PRIVATE;
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
    private String username;
    private String email;
    private MemberRole memberRole;


    @Builder(access = PRIVATE)
    public Member(Long id, String name, String username, String email, MemberRole memberRole) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.memberRole = memberRole;
    }

    public static Member of(OAuth2ResponseDto oAuth2ResponseDto) {
        return Member.builder()
                .name(oAuth2ResponseDto.getName())
                .username(oAuth2ResponseDto.getProvider() + " " + oAuth2ResponseDto.getProviderId())
                .email(oAuth2ResponseDto.getEmail())
                .memberRole(MemberRole.DEFAULT)
                .build();
    }
}
