package com.mentink.hackathon.dto;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Mento;

public class MentoDTO {

    private Long userId;


    public Mento toEntity() {
        Mentee mentee = new Mentee();
        mentee.setId(userId);
        return Mento.builder().mentee(mentee).build();
    }
}
