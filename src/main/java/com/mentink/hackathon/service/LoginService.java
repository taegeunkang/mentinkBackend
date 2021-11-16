package com.mentink.hackathon.service;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Profile;
import com.mentink.hackathon.dto.MenteeDTO;
import com.mentink.hackathon.dto.ProfileDTO;
import com.mentink.hackathon.repository.MenteeRepository;
import com.mentink.hackathon.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final MenteeRepository menteeRepository;
    private final ProfileRepository profileRepository;

    public void signup(MenteeDTO menteeDTO, ProfileDTO profileDTO) {
        String pwd = menteeDTO.getPassword();
        pwd = passwordEncoder.encode(pwd);
        menteeDTO.setPassword(pwd);
        Mentee mentee = menteeDTO.toEntity();
        Profile profile = profileDTO.toEntity(mentee);

        menteeRepository.save(mentee);
        profileRepository.save(profile);
        log.info("username("+menteeDTO.getUserName()+") is signed up");


    }

}
