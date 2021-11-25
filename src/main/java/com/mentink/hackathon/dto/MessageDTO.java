package com.mentink.hackathon.dto;

import com.mentink.hackathon.domain.Matching;
import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private String content;
    private Long matchingId;
    private Long fromId;
    private Long toId;

    public Message toEntity() {
        Mentee from = new Mentee();
        Mentee to = new Mentee();
        Matching matching = new Matching();
        matching.setId(matchingId);
        from.setId(fromId);
        to.setId(toId);
        return Message.builder().from(from)
                .to(to)
                .matching(matching)
                .content(content).build();

    }


}
