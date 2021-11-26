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
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void test() {
        List<Object[]> objects = reviewRepository.getMentoesOrderByRating();
        objects.stream().forEach(objects1 ->
        {
            System.out.println("ID:"+ String.valueOf(objects1[0]));
            System.out.println("Rating:"+ String.valueOf(objects1[1]));
        });
    }

}