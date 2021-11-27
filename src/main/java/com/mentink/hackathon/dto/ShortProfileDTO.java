package com.mentink.hackathon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class ShortProfileDTO {

    private String nickName;
    private String profileImage;
    private boolean verified;

    @Builder
    private ShortProfileDTO(String nickName, String profileImage, boolean verified){
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.verified = verified;
    }

}
