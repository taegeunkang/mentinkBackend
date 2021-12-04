package com.mentink.hackathon.dto;


import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.community.Board;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class BoardDTO {
    private Long writerId;
    private String content;
    private List<MultipartFile> Images;

    public Board toEntity() {
        Mentee mentee = new Mentee();
        mentee.setId(writerId);
        return Board.builder().writer(mentee).content(content)
                .build();
    }
}
