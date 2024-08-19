package com.mysettlement.login;

import com.mysettlement.member.entity.Member;
import com.mysettlement.member.entity.MemberRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2UserDto {

    private String name;
    private String userName;
    private MemberRole memberRole;
    private String email;

    @Builder(access = AccessLevel.PRIVATE)
    private OAuth2UserDto(String name, String userName, String email, MemberRole memberRole) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.memberRole = memberRole;
    }

    public static OAuth2UserDto of(Member member) {
        return OAuth2UserDto.builder()
                .name(member.getName())
                .userName(member.getUsername())
                .email(member.getEmail())
                .memberRole(member.getMemberRole())
                .build();
    }

    public OAuth2UserDto(String username, String role) {
        this.userName = username;
        this.memberRole = MemberRole.valueOf(role);
    }
}
