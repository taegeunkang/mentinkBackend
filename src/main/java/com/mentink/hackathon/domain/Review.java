package com.mentink.hackathon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Integer rating;
    private String content;

    @ManyToOne
    private Mentee mentee;
    @ManyToOne
    private Mento mento;
    @ManyToOne
    private Matching matching;

    @Builder
    private Review(Integer rating, String content,Mento mento, Matching matching, Mentee mentee) {
        this.rating =rating;
        this.content = content;
        this.mentee = mentee;
        this.mento = mento;
        this.matching = matching;

    }


}
