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
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double filesize;
    private String path;

    @OneToOne
    private Mentee mentee;

    @Builder
    private ProfileImage(double filesize,
                         String path,
                         Mentee mentee) {
        this.filesize = filesize;
        this.path = path;
        this.mentee = mentee;
    }

}
