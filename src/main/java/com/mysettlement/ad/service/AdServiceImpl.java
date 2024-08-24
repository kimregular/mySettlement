package com.mysettlement.ad.service;

import com.mysettlement.ad.entity.Ad;
import com.mysettlement.ad.exception.InvalidAdUpdateRequestException;
import com.mysettlement.ad.exception.NoAdFoundException;
import com.mysettlement.ad.repository.AdRepository;
import com.mysettlement.ad.request.AdStatusUpdateReqeustDto;
import com.mysettlement.ad.request.AdUploadRequestDto;
import com.mysettlement.ad.response.AdResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mysettlement.ad.entity.AdStatus.isAvailableStatus;

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
}
