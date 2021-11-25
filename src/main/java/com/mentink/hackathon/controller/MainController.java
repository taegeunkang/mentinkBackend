package com.mentink.hackathon.controller;

import com.mentink.hackathon.domain.Mento;
import com.mentink.hackathon.domain.Review;
import com.mentink.hackathon.dto.MatchingDTO;
import com.mentink.hackathon.dto.MentoDTO;
import com.mentink.hackathon.dto.ReviewDTO;
import com.mentink.hackathon.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.MathContext;
import java.util.List;


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
    public List<Mento> getMentoes() {
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



}
