package com.nanum.market.repository;

import com.nanum.market.model.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart findByBoardIdAndUserId(Long board_id, Long user_id);

    @Transactional
    void deleteByBoardIdAndUserId(Long board_id,Long user_id);

    List<Heart> findByBoardId(Long boardId);
}
