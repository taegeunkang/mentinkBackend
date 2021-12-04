package com.mentink.hackathon.domain.community;

import com.mentink.hackathon.domain.BaseTimeEntity;
import com.mentink.hackathon.domain.Mentee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
    @ManyToOne
    private Mentee writer;
    @ManyToOne
    private Board board;

    @Builder
    private Comment(String content, Mentee writer, Board board) {
        this.content = content;
        this.writer = writer;
        this.board = board;

    }

}
