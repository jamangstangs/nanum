package com.nanum.market.dto;

import com.nanum.market.model.Board;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardCommentDto {
    private Long boardId;
    private String imgUrl;
    private Boolean owned;
    private List<CommentDto> commentDtoList;

    public BoardCommentDto(Long boardId, String imgUrl, Boolean owned, List<CommentDto> commentDtoList) {
        this.boardId = boardId;
        this.imgUrl = imgUrl;
        this.owned = owned;
        this.commentDtoList = commentDtoList;
    }
}
