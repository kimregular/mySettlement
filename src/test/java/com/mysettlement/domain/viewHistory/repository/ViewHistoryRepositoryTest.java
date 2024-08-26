package com.mysettlement.domain.viewHistory.repository;

import com.mysettlement.domain.viewHistory.entity.ViewHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = {"classpath:sql/userData.sql", "classpath:sql/videoData.sql", "classpath:sql/viewHistoryData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ActiveProfiles("test")
@DataJpaTest
class ViewHistoryRepositoryTest {

    @Autowired
    private ViewHistoryRepository viewHistoryRepository;

    @Test
    @DisplayName("2번 유저가 1번 비디오를 본 기록이 있다.")
    void testFindByUserIdAndVideoId() {
        // given
        Long USER = 2L;
        Long VIDEO = 1L;

        // when
        Optional<ViewHistory> foundHistory = viewHistoryRepository.findByUserIdAndVideoId(USER, VIDEO);
        // then
        assertThat(foundHistory).isPresent();
    }
}