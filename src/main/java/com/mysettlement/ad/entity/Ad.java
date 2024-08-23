package com.mysettlement.ad.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "AD")
@NoArgsConstructor(access = PROTECTED)
public class Ad {

    @Id
    @Column(name = "ad_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "ad_view")
    private long adView;

    @Column(name = "ad_status")
    @Enumerated(value = STRING)
    private AdStatus adStatus;

    @Column(name = "ad_price_per_view")
    private double adPricePerView;

    @Builder
    private Ad(long adView, AdStatus adStatus, double adPricePerView) {
        this.adView = adView;
        this.adStatus = adStatus;
        this.adPricePerView = adPricePerView;
    }
}
