package com.mysettlement.domain.viewHistory.controller;

import com.mysettlement.domain.viewHistory.dto.request.ViewVideoRequestDto;
import com.mysettlement.domain.viewHistory.entity.ViewHistory;
import com.mysettlement.domain.viewHistory.service.ViewHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/view")
@RestController
public class ViewHistoryController {

    private final ViewHistoryServiceImpl viewHistoryService;

    @PostMapping
    public ViewHistory viewVideo(@RequestParam Long videoId,
                                 @RequestBody ViewVideoRequestDto viewVideoRequestDto) {
        return viewHistoryService.viewVideo(videoId, viewVideoRequestDto);
    }
}
