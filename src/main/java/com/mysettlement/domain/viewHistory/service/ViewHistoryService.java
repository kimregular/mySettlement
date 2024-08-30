package com.mysettlement.domain.viewHistory.service;

import com.mysettlement.domain.viewHistory.dto.request.ViewVideoRequestDto;
import com.mysettlement.domain.viewHistory.dto.response.ViewHistoryResponseDto;

public interface ViewHistoryService {
    ViewHistoryResponseDto viewVideo(Long videoId, ViewVideoRequestDto viewVideoRequestDto);
}
