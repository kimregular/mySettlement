package com.mysettlement.video.service;

import com.mysettlement.video.request.VideoStatusChangeRequestDto;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.response.VideoResponseDto;

public interface VideoService {

    VideoResponseDto uploadVideo(VideoUploadRequestDto videoUploadRequestDto);

    VideoResponseDto chageStatus(Long videoId, VideoStatusChangeRequestDto videoStatusChangeRequestDto);
}
