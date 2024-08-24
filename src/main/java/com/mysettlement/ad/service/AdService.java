package com.mysettlement.ad.service;

import com.mysettlement.ad.request.AdUploadRequestDto;
import com.mysettlement.ad.response.AdResponseDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AdService {

    @Transactional
    AdResponseDto uploadAd(AdUploadRequestDto adUploadRequestDto);

    AdResponseDto findAdById(Long adId);
}
