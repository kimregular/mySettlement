package com.mysettlement.domain.video.service;

import com.mysettlement.domain.video.dto.request.VideoStatusChangeRequestDto;
import com.mysettlement.domain.video.dto.request.VideoUpdateRequestDto;
import com.mysettlement.domain.video.dto.request.VideoUploadRequestDto;
import com.mysettlement.domain.video.dto.response.VideoResponseDto;

import java.util.List;

public interface VideoService {

    VideoResponseDto uploadVideo(Long userId, VideoUploadRequestDto videoUploadRequestDto);

    VideoResponseDto chageStatus(Long videoId, VideoStatusChangeRequestDto videoStatusChangeRequestDto);

    VideoResponseDto update(Long videoId, VideoUpdateRequestDto videoUpdateRequestDto);

    List<VideoResponseDto> searchVideosOf(String username);
}
