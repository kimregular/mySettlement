package com.mysettlement.domain.ad.service;

import com.mysettlement.domain.ad.entity.Ad;
import com.mysettlement.domain.ad.exception.InvalidAdUpdateRequestException;
import com.mysettlement.domain.ad.exception.NoAdFoundException;
import com.mysettlement.domain.ad.repository.AdRepository;
import com.mysettlement.domain.ad.dto.request.AdStatusUpdateReqeustDto;
import com.mysettlement.domain.ad.dto.request.AdUpdateReqeustDto;
import com.mysettlement.domain.ad.dto.request.AdUploadRequestDto;
import com.mysettlement.domain.ad.dto.response.AdResponseDto;
import com.mysettlement.global.util.AdSelecter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mysettlement.domain.ad.entity.AdStatus.isAvailableStatus;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdSelecter adSelecter;

    @Override
    public AdResponseDto getAdById(Long adId) {
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

    @Override
    public List<Long> getAdsForVideos(Long videoLength) {
        return adRepository.findAdsForRange(adSelecter.getNumOfAds(videoLength)).stream().map(Ad::getId).toList();
    }
}
