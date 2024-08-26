package com.mysettlement.domain.ad.service;

import com.mysettlement.domain.ad.entity.Ad;
import com.mysettlement.domain.ad.exception.InvalidAdUpdateRequestException;
import com.mysettlement.domain.ad.exception.NoAdFoundException;
import com.mysettlement.domain.ad.repository.AdRepository;
import com.mysettlement.domain.ad.request.AdStatusUpdateReqeustDto;
import com.mysettlement.domain.ad.request.AdUpdateReqeustDto;
import com.mysettlement.domain.ad.request.AdUploadRequestDto;
import com.mysettlement.domain.ad.response.AdResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mysettlement.domain.ad.entity.AdStatus.isAvailableStatus;

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

    @Override
    @Transactional
    public AdResponseDto changeStatus(Long adId, AdStatusUpdateReqeustDto adStatusUpdateReqeustDto) {
        if (!isAvailableStatus(adStatusUpdateReqeustDto.adStatus())) {
            throw new InvalidAdUpdateRequestException();
        }
        Ad foundAd = adRepository.findById(adId).orElseThrow(NoAdFoundException::new);
        foundAd.update(adStatusUpdateReqeustDto);
        return AdResponseDto.of(foundAd);
    }

    @Override
    @Transactional
    public AdResponseDto changeInfo(Long adId, AdUpdateReqeustDto adUpdateReqeustDto) {
        Ad foundAd = adRepository.findById(adId).orElseThrow(NoAdFoundException::new);
        foundAd.update(adUpdateReqeustDto);
        return AdResponseDto.of(foundAd);
    }
}
