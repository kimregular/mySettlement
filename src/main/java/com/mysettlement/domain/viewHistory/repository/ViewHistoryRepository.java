package com.mysettlement.domain.viewHistory.repository;

import com.mysettlement.domain.viewHistory.entity.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {

    Optional<ViewHistory> findByUserIdAndVideoId(Long userId, Long videoId);
}
