package com.mysettlement.jwt;

import com.mysettlement.member.entity.Member;
import com.mysettlement.member.entity.MemberRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2UserDto {

    private final String name;
    private final String userName;
    private final String email;
    private final MemberRole memberRole;

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
}
