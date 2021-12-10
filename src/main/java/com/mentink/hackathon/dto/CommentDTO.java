package com.mentink.hackathon.dto;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.community.Board;
import com.mentink.hackathon.domain.community.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long writerId;
    private Long boardId;
    private String content;


    public Comment toEntity() {
        Board board = new Board();
        board.setId(boardId);
        Mentee mentee = new Mentee();
        mentee.setId(writerId);

        return Comment.builder().content(content).writer(mentee)
                .board(board)
                .build();
    }
}
