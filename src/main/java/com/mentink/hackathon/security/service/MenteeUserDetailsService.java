package com.mentink.hackathon.security.service;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.repository.MenteeRepository;
import com.mentink.hackathon.security.dto.MenteeAuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenteeUserDetailsService implements UserDetailsService {

    private final MenteeRepository menteeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userdeatilservice 호출  ID: " + username);

        Optional<Mentee> result = Optional.ofNullable(menteeRepository.findByUserName(username));
        if(result.isEmpty()) {
            throw new UsernameNotFoundException("아이디 또는 비밀번호를 확인하세요.");
        }

        Mentee mentee = result.get();
        log.info("---------user info--------");
        log.info(mentee.toString());
        MenteeAuthDTO menteeAuthDTO = new MenteeAuthDTO(mentee.getUserName(),
                mentee.getPassword(),
                mentee.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet()));
//        log.info(menteeAuthDTO.getAuthorities().toString());

        return menteeAuthDTO;
    }
}
