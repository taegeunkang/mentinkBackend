package com.mentink.hackathon.dto;

import com.mentink.hackathon.domain.Matching;
import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Mento;
import com.mentink.hackathon.domain.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {

    private Integer rating;
    private Long matchingId;
    private Long mentoId;
    private Long menteeId;
    private String content;

    public Review toEntity() {
        Matching matching = new Matching();
        matching.setId(matchingId);
        Mento mento = new Mento();
        mento.setId(mentoId);
        Mentee mentee = new Mentee();
        mentee.setId(menteeId);
        return Review.builder().matching(matching)
                .content(content)
                .rating(rating)
                .mento(mento)
                .mentee(mentee)
                .build();
    }

}

