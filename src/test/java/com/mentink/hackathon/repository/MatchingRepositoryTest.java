package com.mentink.hackathon.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatchingRepositoryTest {
    @Autowired
    private MatchingRepository matchingRepository;

    @Test
    public void 테() {
        List<Object[]> objects = matchingRepository.findSchedule(2L);
        for(Object[] objects1 : objects) {
            System.out.println(objects1[0]);
        }
    }



}