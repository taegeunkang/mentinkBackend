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
public class Message extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    private Matching matching;
    @Column(nullable = false)
    private String content;
    @ManyToOne
    private Mentee from;
    @ManyToOne
    private Mentee to;

    @Builder
    private Message(String content, Matching matching,Mentee from, Mentee to) {
        this.content = content;
        this.matching = matching;
        this.from = from;
        this.to = to;
    }

}
