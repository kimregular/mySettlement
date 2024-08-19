package com.mysettlement.jwt;

import com.mysettlement.member.entity.MemberRole;
import lombok.Getter;

@Getter
public class OAuth2UserDto {

    private final MemberRole memberRole;
    private final String name;
    private final String userName;

    public OAuth2UserDto(MemberRole memberRole, String name, String userName) {
        this.memberRole = memberRole;
        this.name = name;
        this.userName = userName;
    }
}
