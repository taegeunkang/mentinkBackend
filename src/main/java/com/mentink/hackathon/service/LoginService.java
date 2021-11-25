package com.mentink.hackathon.service;

import com.mentink.hackathon.domain.Logout;
import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Profile;
import com.mentink.hackathon.dto.MenteeDTO;
import com.mentink.hackathon.dto.ProfileDTO;
import com.mentink.hackathon.repository.LogoutRepository;
import com.mentink.hackathon.repository.MenteeRepository;
import com.mentink.hackathon.repository.ProfileRepository;
import com.mentink.hackathon.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final MenteeRepository menteeRepository;
    private final ProfileRepository profileRepository;
    private final LogoutRepository logoutRepository;
    private final JWTUtil jwtUtil;
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
    public Optional<Mentee> getUsername(Long userId) {
        return menteeRepository.findById(userId);
    }
    public void logout(String token, String refreshToken,Date tokenExp, Date refreshTokenExp) {
        Logout logout = Logout.builder().token(token).expireDate(tokenExp).build();
        Logout logout1 = Logout.builder().token(refreshToken).expireDate(refreshTokenExp).build();
        logoutRepository.save(logout);
        logoutRepository.save(logout1);

    }

}
