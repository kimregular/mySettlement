package com.mysettlement.video.repository;

import com.mysettlement.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findAllByUserId(Long id);
}
