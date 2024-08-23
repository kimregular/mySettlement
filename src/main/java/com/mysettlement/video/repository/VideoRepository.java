package com.mysettlement.video.repository;

import com.mysettlement.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
