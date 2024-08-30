package com.mysettlement.domain.ad.repository;

import com.mysettlement.domain.ad.entity.Ad;
import com.mysettlement.domain.user.repository.UserRepository;
import com.mysettlement.domain.video.entity.Video;
import com.mysettlement.domain.video.repository.VideoRepository;
import com.mysettlement.global.util.AdSelecter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = {"classpath:sql/userData.sql", "classpath:sql/videoData.sql", "classpath:sql/viewHistoryData.sql", "classpath:sql/adData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ActiveProfiles("test")
@SpringBootTest
class AdRepositoryTest {

    @Autowired
    private AdSelecter adSelecter;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("5분 미만 영상에서는 광고 1개를 보여준다.")
    void testFindAdsForRange(){
        // given
        Video testVideo = Video.builder()
                .videoTitle("testVideo")
                .videoLength(120L)
                .user(userRepository.findById(1L).get())
                .videoDesc("test video legnth 2min")
                .build();
        Video savedVideo = videoRepository.save(testVideo);
        long numOfAds = adSelecter.getNumOfAds(savedVideo.getVideoLength());
        // when
        List<Ad> adsForRange = adRepository.findAdsForRange(numOfAds);
        // then
        assertThat(numOfAds).isOne();
        assertThat(adsForRange).hasSize(1);
    }

    @Test
    @DisplayName("6분 영상에서는 광고 1개를 보여준다.")
    void testFindAdsForRange1(){
        // given
        Video testVideo = Video.builder()
                .videoTitle("testVideo")
                .videoLength(360L)
                .user(userRepository.findById(1L).get())
                .videoDesc("test video legnth 2min")
                .build();
        Video savedVideo = videoRepository.save(testVideo);
        long numOfAds = adSelecter.getNumOfAds(savedVideo.getVideoLength());
        // when
        List<Ad> adsForRange = adRepository.findAdsForRange(numOfAds);
        // then
        assertThat(numOfAds).isOne();
        assertThat(adsForRange).hasSize(1);
    }

    @Test
    @DisplayName("10분 영상에서는 광고 2개를 보여준다.")
    void testFindAdsForRange2(){
        // given
        Video testVideo = Video.builder()
                .videoTitle("testVideo")
                .videoLength(60 * 10)
                .user(userRepository.findById(1L).get())
                .videoDesc("test video legnth 2min")
                .build();
        Video savedVideo = videoRepository.save(testVideo);
        long numOfAds = adSelecter.getNumOfAds(savedVideo.getVideoLength());
        // when
        List<Ad> adsForRange = adRepository.findAdsForRange(numOfAds);
        // then
        assertThat(adsForRange).hasSize(2);
    }

    @Test
    @DisplayName("9분 59초 영상에서는 광고 1개를 보여준다.")
    void testFindAdsForRange3(){
        // given
        Video testVideo = Video.builder()
                .videoTitle("testVideo")
                .videoLength(60 * 9 + 59)
                .user(userRepository.findById(1L).get())
                .videoDesc("test video legnth 2min")
                .build();
        Video savedVideo = videoRepository.save(testVideo);
        long numOfAds = adSelecter.getNumOfAds(savedVideo.getVideoLength());
        // when
        List<Ad> adsForRange = adRepository.findAdsForRange(numOfAds);
        // then
        assertThat(numOfAds).isOne();
        assertThat(adsForRange).hasSize(1);
    }

    @Test
    @DisplayName("15분 영상에서는 광고 3개를 보여준다.")
    void testFindAdsForRange4(){
        // given
        Video testVideo = Video.builder()
                .videoTitle("testVideo")
                .videoLength(60 * 15)
                .user(userRepository.findById(1L).get())
                .videoDesc("test video legnth 2min")
                .build();
        Video savedVideo = videoRepository.save(testVideo);
        long numOfAds = adSelecter.getNumOfAds(savedVideo.getVideoLength());
        // when
        List<Ad> adsForRange = adRepository.findAdsForRange(numOfAds);
        // then
        assertThat(adsForRange).hasSize(3);
    }

}