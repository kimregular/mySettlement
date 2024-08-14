package com.mysettlement.config.auth;

import com.mysettlement.member.entity.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {

    private final String name;
    private final String email;

    public SessionMember(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
