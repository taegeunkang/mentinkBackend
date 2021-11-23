package com.mentink.hackathon.dto;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Mento;

public class MentoDTO {

    private Long userId;
    private String content;
    private String preferredLocation;
    private boolean untact;


    public Mento toEntity() {
        Mentee mentee = new Mentee();
        mentee.setId(userId);
        return Mento.builder().mentee(mentee).preferredLocation(preferredLocation)
                .untact(untact)
                .build();
    }
}
