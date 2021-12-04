package com.mentink.hackathon.domain.community;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mentink.hackathon.domain.BaseTimeEntity;
import com.mentink.hackathon.domain.Mentee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne
    private Mentee writer;

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardImage> boardImages;

    @Builder
    private Board(Mentee writer, String content) {
        this.writer = writer;
        this.content = content;
    }




}
