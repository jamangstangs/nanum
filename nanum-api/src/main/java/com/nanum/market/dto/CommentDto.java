package com.nanum.market.dto;

import com.nanum.market.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String comment;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();}

    public void update(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
    }
}
