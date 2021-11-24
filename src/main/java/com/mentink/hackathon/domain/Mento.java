package com.mentink.hackathon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Mento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String preferredLocation;
    private boolean untact;

    @OneToOne
    private Mentee mentee;

    @Builder
    private Mento(String content, String preferredLocation, boolean untact, Mentee mentee) {
        this.content = content;
        this.preferredLocation = preferredLocation;
        this.untact = untact;
        this.mentee = mentee;
    }
}
