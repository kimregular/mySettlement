package com.mysettlement.domain.viewHistory.entity;

import com.mysettlement.domain.user.entity.User;
import com.mysettlement.domain.video.entity.Video;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "VIEW_HISTORY")
@NoArgsConstructor(access = PROTECTED)
public class ViewHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "view_history_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @Column(name = "user_id")
    private User user;

    @OneToOne(fetch = LAZY)
    @Column(name = "video_id")
    private Video video;

    @Column(name = "view_date")
    private LocalDateTime viewDate;

    @Builder
    private ViewHistory(User user, Video video, LocalDateTime viewDate) {
        this.user = user;
        this.video = video;
        this.viewDate = viewDate;
    }
}
