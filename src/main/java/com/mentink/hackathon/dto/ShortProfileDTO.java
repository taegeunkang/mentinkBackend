package com.mentink.hackathon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class ShortProfileDTO {

    private String nickName;
    private byte[] profileImage;

}
