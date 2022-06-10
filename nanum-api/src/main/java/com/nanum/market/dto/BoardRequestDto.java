package com.nanum.market.dto;

import com.nanum.market.model.Board;
import lombok.Getter;

@Getter
public class
BoardRequestDto {
    private String title;
    private String content;
    private boolean status;
    private String imgUrl;

    public BoardRequestDto(String title, String content,boolean status, String imgUrl) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public BoardRequestDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.imgUrl = board.getImgUrl();
        this.status = board.isStatus();
    }
}
