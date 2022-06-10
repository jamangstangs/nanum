package com.nanum.market.repository;

import com.nanum.market.model.Board;
import com.nanum.market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();

    List<Board> findByTitleContainingOrContentContainingOrderByModifiedAtDesc(String title, String title1);

    List<Board> findByUser(User user);
}
