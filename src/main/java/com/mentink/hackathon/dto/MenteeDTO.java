package com.mentink.hackathon.dto;


import com.mentink.hackathon.domain.Mentee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MenteeDTO {
    private String userName;
    private String password;
    private String email;

    public MenteeDTO(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Mentee toEntity() {
        return Mentee.builder()
                .userName(userName)
                .password(password)
                .email(email)
                .build();
    }
}
