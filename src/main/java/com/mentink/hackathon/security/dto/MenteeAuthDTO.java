package com.mentink.hackathon.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Slf4j
@Getter
@Setter
@ToString
public class MenteeAuthDTO extends User {

    private String username;
    private String password;

    public MenteeAuthDTO(String username, String password, Collection<? extends GrantedAuthority> authorites) {
        super(username,password, authorites);
        this.username = username;
        this.password = password;
    }
}
