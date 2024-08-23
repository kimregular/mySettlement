package com.mysettlement.ad.repository;

import com.mysettlement.ad.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
