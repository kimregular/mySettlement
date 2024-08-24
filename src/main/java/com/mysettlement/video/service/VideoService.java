package com.mysettlement.video.service;

import com.mysettlement.video.request.VideoStatusChangeRequestDto;
import com.mysettlement.video.request.VideoUpdateRequestDto;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.response.VideoResponseDto;

import java.util.List;

public interface VideoService {

    VideoResponseDto uploadVideo(Long userId, VideoUploadRequestDto videoUploadRequestDto);

    VideoResponseDto chageStatus(Long videoId, VideoStatusChangeRequestDto videoStatusChangeRequestDto);

    VideoResponseDto update(Long videoId, VideoUpdateRequestDto videoUpdateRequestDto);

    List<VideoResponseDto> findVideosOf(String username);
}
