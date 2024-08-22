package com.mysettlement.video.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VideoStatus {

    ACTIVE("시청"),
    ARCHIVED("보관"),
    DELETED("삭제");

    private final String status;
}
