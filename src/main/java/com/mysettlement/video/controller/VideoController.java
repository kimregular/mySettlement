package com.mysettlement.video.controller;

import com.mysettlement.globalResponse.MySettlementGlobalResponse;
import com.mysettlement.video.exception.InvalidVideoUploadRequestException;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.response.VideoResponseDto;
import com.mysettlement.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
