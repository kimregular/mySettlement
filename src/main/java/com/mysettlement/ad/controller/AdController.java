package com.mysettlement.ad.controller;

import com.mysettlement.ad.exception.InvalidAdUploadRequestException;
import com.mysettlement.ad.request.AdUploadRequestDto;
import com.mysettlement.ad.response.AdResponseDto;
import com.mysettlement.ad.service.AdService;
import com.mysettlement.globalResponse.MySettlementGlobalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ad")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    @GetMapping("/{adId}")
    public MySettlementGlobalResponse<AdResponseDto> searchAdWith(@PathVariable Long adId) {
        return MySettlementGlobalResponse.success(adService.findAdById(adId));
    }

    @PostMapping
    public MySettlementGlobalResponse<AdResponseDto> uploadAd(@Valid @RequestBody AdUploadRequestDto adUploadRequestDto, BindingResult errors) {
        if(errors.hasErrors()) throw new InvalidAdUploadRequestException(errors);
        return MySettlementGlobalResponse.of(HttpStatus.OK, adService.uploadAd(adUploadRequestDto));
    }
}
