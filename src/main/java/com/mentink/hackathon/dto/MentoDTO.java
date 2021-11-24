package com.mentink.hackathon.dto;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Mento;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public class MentoDTO {

    private Long userId;
    private String content;
    private String preferredLocation;
    private boolean untact;

    public MentoDTO(Long userId, String content, String preferredLocation, boolean untact) {
        this.userId = userId;
        this.content = content;
        this.preferredLocation = preferredLocation;
        this.untact = untact;
    }

    public Mento toEntity() {
        Mentee mentee = new Mentee();
        mentee.setId(userId);
        return Mento.builder().mentee(mentee).preferredLocation(preferredLocation)
                .content(content)
                .untact(untact)
                .build();
    }
}
