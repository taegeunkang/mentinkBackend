package com.mentink.hackathon.controller;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Mento;
import com.mentink.hackathon.domain.Message;
import com.mentink.hackathon.domain.Review;
import com.mentink.hackathon.dto.MatchingDTO;
import com.mentink.hackathon.dto.MentoDTO;
import com.mentink.hackathon.dto.MessageDTO;
import com.mentink.hackathon.dto.ReviewDTO;
import com.mentink.hackathon.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/app")
@RestController
public class MainController {
    private final AppService appService;


    @GetMapping("test")
    public String test() {
        return "login successed";
    }

    @PostMapping("/mentolist")
    public List<Map<String, String>> getMentoes() throws IOException {
        return appService.getAllMentos();
    }

    @PostMapping("/matching")
    public ResponseEntity setMatching(@RequestBody MatchingDTO matching) {
        appService.setMatching(matching);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/matching/review")
    public ResponseEntity setReview(@RequestBody ReviewDTO review) {
        appService.setReview(review);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/matching/review/{mentoId}")
    public List<Review> getRiview(@PathVariable("mentoId") Long mentoId) {
        return appService.getReview(mentoId);
    }

    @PostMapping("/matching/message")
    public List<Map<String, String>> setMessage(@RequestBody MessageDTO messageDTO) {
        List<Object[]> messageList = appService.setMessage(messageDTO);
        List<Map<String, String>> maplist = new ArrayList<>();
        for(Object[] message : messageList) {
            Map<String, String> mp = new HashMap<>();
            Mentee from = (Mentee)message[2];
            Mentee to = (Mentee)message[3];
            mp.put("id", String.valueOf(message[0]));
            mp.put("content", String.valueOf(message[1]));
            mp.put("from", String.valueOf(from.getId()));
            mp.put("to", String.valueOf(to.getId()));
            mp.put("createdTime", String.valueOf(message[4]));
            maplist.add(mp);
        }
        return maplist;
    }


}
