package com.mysettlement.domain.ad.repository;

import com.mysettlement.domain.ad.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
