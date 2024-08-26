package com.mysettlement.domain.ad.service;

import com.mysettlement.domain.ad.request.AdStatusUpdateReqeustDto;
import com.mysettlement.domain.ad.request.AdUpdateReqeustDto;
import com.mysettlement.domain.ad.request.AdUploadRequestDto;
import com.mysettlement.domain.ad.response.AdResponseDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AdService {

    @Transactional
    AdResponseDto uploadAd(AdUploadRequestDto adUploadRequestDto);

    AdResponseDto findAdById(Long adId);

    @Transactional
    AdResponseDto changeStatus(Long adId, AdStatusUpdateReqeustDto adStatusUpdateReqeustDto);

    AdResponseDto changeInfo(Long adId, AdUpdateReqeustDto adUpdateReqeustDto);
}
