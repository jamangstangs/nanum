package com.nanum.market.dto;

import com.nanum.market.model.Board;
import lombok.Getter;

@Getter
public class BoardPostDto {
    private Long boardId;
    private String imgUrl;

    public BoardPostDto(Board board) {
        this.boardId = board.getId();
        this.imgUrl = board.getImgUrl();
    }
}
