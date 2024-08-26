package com.mysettlement.ad.controller;

import com.mysettlement.ad.request.AdStatusUpdateReqeustDto;
import com.mysettlement.ad.request.AdUpdateReqeustDto;
import com.mysettlement.ad.request.AdUploadRequestDto;
import com.mysettlement.ad.response.AdResponseDto;
import com.mysettlement.ad.service.AdService;
import com.mysettlement.globalResponse.MySettlementGlobalResponse;
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
