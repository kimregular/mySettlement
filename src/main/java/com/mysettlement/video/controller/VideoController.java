package com.mysettlement.video.controller;

import com.mysettlement.video.exception.InvalidVideoUploadRequestException;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.response.VideoResponseDto;
import com.mysettlement.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/video")
public class VideoController {

    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public VideoResponseDto uploadVideo(@Valid @RequestBody VideoUploadRequestDto videoUploadRequestDto, BindingResult errors) {
        if(errors.hasErrors()) throw new InvalidVideoUploadRequestException(errors);
        return videoService.uploadVideo(videoUploadRequestDto);
    }
}
