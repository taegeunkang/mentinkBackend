package com.mentink.hackathon.dto;

public class MenteeJobInfoDTO {
    private String email;
    private String company;
    private String job;

    public MenteeJobInfoDTO(String email, String company, String job){
        this.email = email;
        this.company = company;
        this.job = job;
    }

}
