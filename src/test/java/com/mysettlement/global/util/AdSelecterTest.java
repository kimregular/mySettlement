package com.mysettlement.global.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdSelecterTest {

    @Autowired
    private AdSelecter adSelecter;

    private final Long FIVE_MINUTES_IN_SECONDS = 300L;

    @Test
    @DisplayName("주어진 영상 길이의 5분마다 광고 1개")
    void testGetNumOfAds1() {
        // given
        Long videoLength = 300L;
        // when
        long numOfAds = adSelecter.getNumOfAds(videoLength);
        // then
        assertThat(numOfAds).isEqualTo(videoLength / FIVE_MINUTES_IN_SECONDS);
    }
    
    @Test
    @DisplayName("영상이 5분 미만이면 광고 1개")
    void testGetNumOfAds2(){
        // given
        Long videoLength = 1L;
        // when
        long numOfAds = adSelecter.getNumOfAds(videoLength);
        // then
        assertThat(numOfAds).isOne();
    }
}