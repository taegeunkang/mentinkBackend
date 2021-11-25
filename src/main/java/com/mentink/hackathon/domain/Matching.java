package com.mentink.hackathon.domain;

import javax.persistence.*;

@Entity
public class Matching extends BaseTimeEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    private Mentee mentee;
    @ManyToOne
    private Mento mento;

}
