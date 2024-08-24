package com.mysettlement.ad.service;

import com.mysettlement.ad.entity.Ad;
import com.mysettlement.ad.exception.NoAdFoundException;
import com.mysettlement.ad.repository.AdRepository;
import com.mysettlement.ad.request.AdUploadRequestDto;
import com.mysettlement.ad.response.AdResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    @Override
    public AdResponseDto findAdById(Long adId) {
        Ad foundAd = adRepository.findById(adId).orElseThrow(NoAdFoundException::new);
        return AdResponseDto.of(foundAd);
    }

    @Override
    @Transactional
    public AdResponseDto uploadAd(AdUploadRequestDto adUploadRequestDto) {
        Ad newAd = adRepository.save(Ad.of(adUploadRequestDto));
        return AdResponseDto.of(newAd);
    }
}
