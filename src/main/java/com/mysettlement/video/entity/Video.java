package com.mysettlement.video.entity;

import com.mysettlement.user.entity.User;
import com.mysettlement.video.request.VideoStatusChangeRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
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
    private Video(User user, String videoTitle, String videoDesc, long videoView, double videoPricePerView, VideoStatus videoStatus) {
        this.user = user;
        this.videoTitle = videoTitle;
        this.videoDesc = videoDesc;
        this.videoView = videoView;
        this.videoPricePerView = videoPricePerView;
        this.videoStatus = videoStatus;
    }

    public void update(VideoStatusChangeRequestDto videoStatusChangeRequestDto) {
        this.videoStatus = videoStatusChangeRequestDto.videoStatus();
    }
}
