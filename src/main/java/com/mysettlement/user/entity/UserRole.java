package com.mysettlement.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    GUEST("ROLE_GUEST", "임시"),
    DEFAULT("ROLE_DEFAULT", "일반"),
    UPLOADER("ROLE_UPLOADER", "업로더"),
    ;
    private final String code;
    private final String roleDesc;
}
