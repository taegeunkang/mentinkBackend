package com.mentink.hackathon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Matching extends BaseTimeEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Mentee mentee;
    @ManyToOne(cascade = CascadeType.ALL)
    private Mento mento;

    private String location;

    private Date appointmentTime;

    @JsonIgnore
    @OneToMany(mappedBy = "matching", fetch = FetchType.LAZY)
    private List<Review> reviewList;

    @Builder
    private Matching(Mentee mentee, Mento mento, String location, Date appointmentTime) {
        this.mentee = mentee;
        this.mento = mento;
        this.location = location;
        this.appointmentTime =appointmentTime;
    }

}
