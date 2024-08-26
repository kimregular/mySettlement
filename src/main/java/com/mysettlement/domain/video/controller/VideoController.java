package com.mysettlement.domain.video.controller;

import com.mysettlement.global.response.MySettlementGlobalResponse;
import com.mysettlement.domain.video.dto.request.VideoStatusChangeRequestDto;
import com.mysettlement.domain.video.dto.request.VideoUpdateRequestDto;
import com.mysettlement.domain.video.dto.request.VideoUploadRequestDto;
import com.mysettlement.domain.video.dto.response.VideoResponseDto;
import com.mysettlement.domain.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/video")
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/{userId}/videos")
    public MySettlementGlobalResponse<VideoResponseDto> uploadVideo(@PathVariable Long userId,
                                                                    @Valid @RequestBody VideoUploadRequestDto videoUploadRequestDto) {
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.uploadVideo(userId, videoUploadRequestDto));
    }

    @PatchMapping("/{videoId}/info")
    public MySettlementGlobalResponse<VideoResponseDto> updateVideo(@PathVariable Long videoId,
                                                                    @Valid @RequestBody VideoUpdateRequestDto videoUpdateRequestDto) {
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.update(videoId, videoUpdateRequestDto));
    }

    @PatchMapping("/{videoId}/status")
    public MySettlementGlobalResponse<VideoResponseDto> changeVideoStatus(@PathVariable Long videoId,
                                                                          @Valid @RequestBody VideoStatusChangeRequestDto videoStatusChangeRequestDto) {
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.chageStatus(videoId, videoStatusChangeRequestDto));
    }

    @GetMapping("/{username}/videos")
    public MySettlementGlobalResponse<List<VideoResponseDto>> searchVideosOf(@PathVariable String username) {
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.searchVideosOf(username));
    }
}
