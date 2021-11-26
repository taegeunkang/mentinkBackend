package com.mentink.hackathon.service;

import com.mentink.hackathon.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProfileServiceTest {
    @Autowired
    private ProfileService profileService;
    @Test
    public void hh() {
        profileService.setVerified(2L);

    }

}