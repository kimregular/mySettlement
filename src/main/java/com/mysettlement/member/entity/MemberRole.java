package com.mysettlement.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    ROLE_DEFAULT("일반"),
    ROLE_UPLOADER("업로더"),
    ;
    private final String title;
}
