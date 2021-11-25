package com.mentink.hackathon.service;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.Mento;
import com.mentink.hackathon.domain.Review;
import com.mentink.hackathon.dto.MatchingDTO;
import com.mentink.hackathon.dto.MentoDTO;
import com.mentink.hackathon.dto.ReviewDTO;
import com.mentink.hackathon.repository.MatchingRepository;
import com.mentink.hackathon.repository.MentoRepository;
import com.mentink.hackathon.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppService {
    private final MentoRepository mentoRepository;
    private final MatchingRepository matchingRepository;
    private final ReviewRepository reviewRepository;

    public List<Mento> getAllMentos() {
        List<Mento> mentolist = new ArrayList<>();
        List<Object[]> mentoes = mentoRepository.findMentoBy();
        for(Object[] objects : mentoes) {
            Mentee mt = new Mentee();
            mt.setId((Long)objects[4]);
            Mento mento = new Mento();
            mento.setId((Long)objects[0]);
            mento.setContent(String.valueOf(objects[1]));
            mento.setPreferredLocation(String.valueOf(objects[2]));
            mento.setUntact((boolean)objects[3]);
            mento.setMentee(mt);
            mentolist.add(mento);
        };
        log.info("모든 멘토 목록 출력");
        return mentolist;
    }

    public void setMatching(MatchingDTO matching){
        matchingRepository.save(matching.toEntity());
        log.info("매칭 생성");
    }
    public void setReview(ReviewDTO review) {
        reviewRepository.save(review.toEntity());
        log.info("리뷰 생성");
    }

    public List<Review> getReview(Long mentoId){
        List<Review> lists = new ArrayList<>();
        List<Object[]> objects = reviewRepository.findReviewByMentoId(mentoId);
        for(Object[] objects1 : objects) {
            Review review = new Review();
            review.setId((Long)objects1[0]);
            review.setContent(String.valueOf(objects1[1]));
            review.setRating((Integer)objects1[2]);
            lists.add(review);
        }
        return lists;
    }
}
