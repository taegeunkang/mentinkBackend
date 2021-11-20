package com.mentink.hackathon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Logout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private Date expireDate;

    @Builder
    private Logout(String token, Date expireDate) {
        this.token = token;
        this.expireDate = expireDate;
    }

}
