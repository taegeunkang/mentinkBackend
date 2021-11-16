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
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nickName;

    private String gender;
    private String birth;

    private String company;
    private String job;
    @Column(nullable = false)
    private String school;
    @Column(nullable = false)
    private String major;

    @OneToOne
    private Mentee mentee;

    @Builder
    private Profile(String nickName, String gender, String birth
    , String company, String job, String school, String major, Mentee mentee) {
        this.nickName = nickName;
        this.gender = gender;
        this.birth =birth;
        this.company = company;
        this.job = job;
        this.school = school;
        this.major = major;
        this.mentee = mentee;
    }

}
