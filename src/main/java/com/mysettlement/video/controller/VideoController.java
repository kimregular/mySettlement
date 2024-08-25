package com.mysettlement.video.controller;

import com.mysettlement.globalResponse.MySettlementGlobalResponse;
import com.mysettlement.video.exception.InvalidVideoUpdateRequestException;
import com.mysettlement.video.exception.InvalidVideoUploadRequestException;
import com.mysettlement.video.request.VideoStatusChangeRequestDto;
import com.mysettlement.video.request.VideoUpdateRequestDto;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.response.VideoResponseDto;
import com.mysettlement.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/video/v1")
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/{userId}")
    public MySettlementGlobalResponse<VideoResponseDto> uploadVideo(@PathVariable Long userId,
                                                                    @Valid @RequestBody VideoUploadRequestDto videoUploadRequestDto,
                                                                    BindingResult errors) {
        if(errors.hasErrors()) throw new InvalidVideoUploadRequestException(errors);
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.uploadVideo(userId, videoUploadRequestDto));
    }

    @PatchMapping("/{videoId}/info")
    public MySettlementGlobalResponse<VideoResponseDto> updateVideo(@PathVariable Long videoId,
                                                                    @Valid @RequestBody VideoUpdateRequestDto videoUpdateRequestDto,
                                                                    BindingResult errors) {
        if(errors.hasErrors()) throw new InvalidVideoUpdateRequestException(errors);
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.update(videoId, videoUpdateRequestDto));
    }

    @PatchMapping("/{videoId}/status")
    public MySettlementGlobalResponse<VideoResponseDto> changeVideoStatus(@PathVariable Long videoId,
                                                                          @Valid @RequestBody VideoStatusChangeRequestDto videoStatusChangeRequestDto,
                                                                          BindingResult errors) {
        if (errors.hasErrors()) throw new InvalidVideoUpdateRequestException(errors);
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.chageStatus(videoId, videoStatusChangeRequestDto));
    }

    @GetMapping("/{username}/videos")
    public MySettlementGlobalResponse<List<VideoResponseDto>> searchVideosOf(@PathVariable String username) {
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.searchVideosOf(username));
    }
}
