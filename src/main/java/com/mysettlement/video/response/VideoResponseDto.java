package com.mysettlement.video.response;

import com.mysettlement.user.entity.User;
import com.mysettlement.user.response.UserResponseDto;
import com.mysettlement.video.entity.Video;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VideoResponseDto {

    private final String title;
    private final UserResponseDto user;
    private final String desc;


    @Builder
    private VideoResponseDto(String title, User user, String desc) {
        this.title = title;
        this.user = UserResponseDto.of(user);
        this.desc = desc;
    }

    public static VideoResponseDto of(Video video) {
        return VideoResponseDto.builder()
                .title(video.getVideoTitle())
                .user(video.getUser())
                .desc(video.getVideoDesc())
                .build();
    }
}
