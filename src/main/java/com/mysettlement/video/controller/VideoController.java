package com.mysettlement.video.controller;

import com.mysettlement.globalResponse.MySettlementGlobalResponse;
import com.mysettlement.video.exception.InvalidVideoStatusChangeRequestException;
import com.mysettlement.video.exception.InvalidVideoUploadRequestException;
import com.mysettlement.video.request.VideoStatusChangeRequestDto;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.response.VideoResponseDto;
import com.mysettlement.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public MySettlementGlobalResponse<VideoResponseDto> uploadVideo(@Valid @RequestBody VideoUploadRequestDto videoUploadRequestDto, BindingResult errors) {
        if(errors.hasErrors()) throw new InvalidVideoUploadRequestException(errors);
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.uploadVideo(videoUploadRequestDto));
    }

    @PutMapping("/{videoId}")
    public MySettlementGlobalResponse<VideoResponseDto> changeVideoStatus(@PathVariable Long videoId, @Valid @RequestBody VideoStatusChangeRequestDto videoStatusChangeRequestDto, BindingResult errors) {
        if(errors.hasErrors()) throw new InvalidVideoStatusChangeRequestException(errors);
        return MySettlementGlobalResponse.of(HttpStatus.OK, videoService.chageStatus(videoId, videoStatusChangeRequestDto));
    }
}
