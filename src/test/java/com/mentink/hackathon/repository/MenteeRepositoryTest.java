package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Mentee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MenteeRepositoryTest {

    @Autowired
    private MenteeRepository menteeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 계정생성() {

        Mentee mentee = menteeRepository.findByUserName("dean");
        System.out.println(mentee.getUserName());
        System.out.println(mentee.getPassword());
    }

    @Test
    public void 테스트() {
//        List<Object[]> objects = menteeRepository.findByUserId(4L);
//        System.out.println(objects.get(0));
        System.out.println("hihi");

    }

}