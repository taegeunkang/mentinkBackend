package com.mentink.hackathon.domain.community;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class BoardImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double filesize;
    private String path;

    @ManyToOne
    private Board board;


    @Builder
    private BoardImage(double filesize, String path, Board board) {
        this.filesize = filesize;
        this.path = path;
        this.board = board;
    }


}
