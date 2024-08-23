package com.mysettlement.ad.service;

import com.mysettlement.ad.request.AdUploadRequestDto;
import com.mysettlement.ad.response.AdResponseDto;

public interface AdService {

    AdResponseDto uploadAd(AdUploadRequestDto adUploadRequestDto);
}
