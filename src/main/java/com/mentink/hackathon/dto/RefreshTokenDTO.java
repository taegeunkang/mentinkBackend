package com.mentink.hackathon.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RefreshTokenDTO {
    private Long userId;
    private String refreshToken;

}
