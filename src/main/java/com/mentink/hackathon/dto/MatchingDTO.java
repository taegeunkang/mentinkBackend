package com.mentink.hackathon.dto;

import com.mentink.hackathon.domain.Matching;
import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Mento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
public class MatchingDTO {
    private Long menteeId;
    private Long mentoId;
    private String location;
    private Date appointmentTime;

    public Matching toEntity() {
        Mentee mentee = new Mentee();
        mentee.setId(menteeId);
        Mento mento = new Mento();
        mento.setId(mentoId);

        return Matching.builder().mentee(mentee).mento(mento)
                .location(location).appointmentTime(appointmentTime)
                .build();

    }


}
