package com.mentink.hackathon.dto;


import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.ProfileImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ProfileImageDTO {
    private Long userId;
    private String path;
    private MultipartFile image;
    private double fileSize;

    public ProfileImage toEntity() {
        Mentee mentee = new Mentee();
        mentee.setId(userId);
        return ProfileImage.builder().path(path)
                .filesize(fileSize)
                .mentee(mentee)
                .build();
    }

}
