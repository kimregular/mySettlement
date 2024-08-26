package com.mysettlement.domain.ad.controller;

import com.mysettlement.domain.ad.request.AdStatusUpdateReqeustDto;
import com.mysettlement.domain.ad.request.AdUpdateReqeustDto;
import com.mysettlement.domain.ad.request.AdUploadRequestDto;
import com.mysettlement.domain.ad.response.AdResponseDto;
import com.mysettlement.domain.ad.service.AdService;
import com.mysettlement.global.globalResponse.MySettlementGlobalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ad/")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    @GetMapping("/{adId}")
    public MySettlementGlobalResponse<AdResponseDto> searchAdWith(@PathVariable Long adId) {
        return MySettlementGlobalResponse.success(adService.findAdById(adId));
    }

    @PostMapping
    public MySettlementGlobalResponse<AdResponseDto> uploadAd(@Valid @RequestBody AdUploadRequestDto adUploadRequestDto) {
        return MySettlementGlobalResponse.of(HttpStatus.OK, adService.uploadAd(adUploadRequestDto));
    }

    @PatchMapping("/{adId}/status")
    public MySettlementGlobalResponse<AdResponseDto> updateAd(@PathVariable Long adId,
                                                              @Valid @RequestBody AdStatusUpdateReqeustDto adStatusUpdateReqeustDto) {
        return MySettlementGlobalResponse.success(adService.changeStatus(adId, adStatusUpdateReqeustDto));
    }

    @PatchMapping("/{adId}/info")
    public MySettlementGlobalResponse<AdResponseDto> updateAd(@PathVariable Long adId,
                                                              @Valid @RequestBody AdUpdateReqeustDto adUpdateReqeustDto) {
        return MySettlementGlobalResponse.success(adService.changeInfo(adId, adUpdateReqeustDto));
    }
}
