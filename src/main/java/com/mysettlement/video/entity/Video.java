package com.mysettlement.video.entity;

import com.mysettlement.user.entity.User;
import com.mysettlement.video.request.VideoStatusChangeRequestDto;
import com.mysettlement.video.request.VideoUpdateRequestDto;
import com.mysettlement.video.request.VideoUploadRequestDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "VIDEO")
@NoArgsConstructor(access = PROTECTED)
public class Video {

    @Id
    @Column(name = "video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "video_title")
    private String videoTitle;

    @Column(name = "video_length")
    private long videoLength;

    @Nullable
    @Column(name = "video_desc")
    private String videoDesc;

    @Column(name = "video_view")
    private long videoView;

    @Column(name = "video_price_per_view")
    private double videoPricePerView;

    @Column(name = "video_status")
    @Enumerated(STRING)
    private VideoStatus videoStatus;

    @Builder
    public Video(User user, String videoTitle, long videoLength, @Nullable String videoDesc) {
        this.user = user;
        this.videoTitle = videoTitle;
        this.videoLength = videoLength;
        this.videoDesc = videoDesc;
        this.videoView = 0;
        this.videoPricePerView = 1.0;
        this.videoStatus = VideoStatus.AVAILABLE;
    }

    public static Video of(User user, VideoUploadRequestDto videoUploadRequestDto) {
        return Video.builder()
                .videoTitle(videoUploadRequestDto.title())
                .videoLength(videoUploadRequestDto.videoLength())
                .user(user)
                .videoDesc(videoUploadRequestDto.desc())
                .build();

    }

    public void update(VideoStatusChangeRequestDto videoStatusChangeRequestDto) {
        this.videoStatus = videoStatusChangeRequestDto.videoStatus();
    }

    public void update(VideoUpdateRequestDto videoUpdateRequestDto) {
        this.videoTitle = videoUpdateRequestDto.title();
        this.videoDesc = videoUpdateRequestDto.desc();
    }
}
