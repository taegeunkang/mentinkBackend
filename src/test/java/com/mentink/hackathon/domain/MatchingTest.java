package com.mentink.hackathon.domain;

import com.mentink.hackathon.repository.MatchingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatchingTest {

    @Autowired
    private MatchingRepository matchingRepository;


    @Test
    public void 테스트() {
        Mentee mentee = new Mentee();
        Mento mento = new Mento();
        mentee.setId(1L);
        mento.setId(2L);

    }

}