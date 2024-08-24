package com.mysettlement.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    GUEST("ROLE_GUEST", "임시"),
    DEFAULT("ROLE_DEFAULT", "일반"),
    ;
    private final String code;
    private final String roleDesc;
}
