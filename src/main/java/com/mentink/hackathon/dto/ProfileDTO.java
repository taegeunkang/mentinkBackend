package com.mentink.hackathon.dto;


import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProfileDTO {

    private String nickName;
    private String gender;
    private String birth;
    private String company;
    private String job;
    private String school;
    private String major;

    public ProfileDTO(String nickName, String gender, String birth
            , String company, String job, String school, String major) {
        this.nickName = nickName;
        this.gender = gender;
        this.birth = birth;
        this.company = company;
        this.job = job;
        this.school = school;
        this.major = major;

    }


    public Profile toEntity(Mentee mentee) {
        return Profile.builder()
                .nickName(nickName)
                .gender(gender)
                .birth(birth)
                .company(company)
                .job(job)
                .school(school)
                .major(major)
                .mentee(mentee)
                .build();
    }
}
