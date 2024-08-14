package com.mysettlement.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    DEFAULT("ROLE_DEFAULT", "일반"),
    UPLOADER("ROLE_UPLOADER", "업로더"),
    ;


    private final String role;
    private final String title;
}
