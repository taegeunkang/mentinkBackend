package com.mentink.hackathon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

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


    @JsonIgnore
    @OneToMany(mappedBy = "mento", fetch = FetchType.LAZY)
    private List<Matching> matchingList;

    @Builder
    private Mento(String content, String preferredLocation, boolean untact, Mentee mentee) {
        this.content = content;
        this.preferredLocation = preferredLocation;
        this.untact = untact;
        this.mentee = mentee;
    }
}
