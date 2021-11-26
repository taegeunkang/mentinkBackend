package com.mentink.hackathon.service;

import com.mentink.hackathon.dto.MatchingDTO;
import com.mentink.hackathon.dto.ReviewDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppServiceTest {

    @Autowired
    private AppService appService;


    @Test
    public void 매칭_생성_테스트() throws ParseException {
        MatchingDTO matchingDTO = new MatchingDTO();
        String date = "2021-12-31 11:00:";
        for(int i=0; i<30; i++) {
            String tmp;
            if(i < 10){
               tmp = date +"0"+i;
            }else{
               tmp = date + i;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            matchingDTO.setMenteeId(2L);
            matchingDTO.setMentoId(1L);
            matchingDTO.setAppointmentTime(simpleDateFormat.parse(tmp));
            appService.setMatching(matchingDTO);
        }



    }
    @Test
    public void 리뷰_생성_테스트() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        for(int i=1; i<= 31; i++) {
            for(int j=0; j<10; j++) {
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setMentoId(1L);
                reviewDTO.setContent("너무 친절하셔요!!"+i);
                reviewDTO.setMatchingId((long) i);
                reviewDTO.setRating(random.nextInt(6));
                appService.setReview(reviewDTO);
            }
        }

    }

    @Test
    public void 테테트슷트() throws IOException {
        appService.getSchedule(2L).stream().forEach(mps-> {
            System.out.println(mps.get("nickName"));
            System.out.println(mps.get("appointmentTime"));
            System.out.println(mps.get("profileImage"));
        });
    }
    @Test
    public void 테슷() throws IOException {
        List<Map<String, String>> mp = appService.getAllMentores();
        mp.stream().forEach(ob -> {
            String rating = ob.get("rating");
            System.out.println(rating);
        });
    }

    @Test
    public void 평점() {
        String rating = appService.getReviewAvg(1L);
        System.out.println(rating);
    }

}