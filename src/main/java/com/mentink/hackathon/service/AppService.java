package com.mentink.hackathon.service;

import com.mentink.hackathon.domain.*;
import com.mentink.hackathon.dto.MatchingDTO;
import com.mentink.hackathon.dto.MentoDTO;
import com.mentink.hackathon.dto.MessageDTO;
import com.mentink.hackathon.dto.ReviewDTO;
import com.mentink.hackathon.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppService {
    private final MentoRepository mentoRepository;
    private final MatchingRepository matchingRepository;
    private final ReviewRepository reviewRepository;
    private final MessageRepository messageRepository;
    private final ProfileRepository profileRepository;
    public List<Map<String, String>> getAllMentores() throws IOException {
        List<Mento> mentolist = new ArrayList<>();
        List<Object[]> mentoes = mentoRepository.findMentoBy();

        List<Map<String, String>> mapList = new ArrayList<>();
        for(Object[] objects : mentoes) {
            Map<String, String> mp = new HashMap<>();
            List<Object[] > obj = profileRepository.findProfileInfoShort((Long)objects[0]);
            Object[] object = obj.get(0);
            String nickname = String.valueOf(object[0]);
            String path = String.valueOf(object[1]);
            InputStream inputStream = new FileInputStream(path);
            byte[] imageToByteArray = IOUtils.toByteArray(inputStream);
            String img = byteArraytoBase64(imageToByteArray);
            inputStream.close();
            String rating = getReviewAvg(Long.parseLong(String.valueOf(objects[0])));
            mp.put("id", String.valueOf(objects[0]));
            mp.put("nickName", nickname);
            mp.put("profileImage",img);
            mp.put("content", String.valueOf(objects[1]));
            mp.put("preferredLocation", String.valueOf(objects[2]));
            mp.put("untact", String.valueOf((boolean)objects[3]));
            mp.put("mentee_id", String.valueOf(objects[4]));
            mp.put("rating", rating);

            mapList.add(mp);
        };
        log.info("모든 멘토 목록 출력");
        return mapList;
    }
    public List<Map<String, String>> getSchedule(Long menteeId) throws IOException {
        List<Map<String, String>> mapList = new ArrayList<>();
        List<Object[]> objects = matchingRepository.findSchedule(menteeId);
        for(Object[] obj: objects){
            Map<String, String> mp = new HashMap<>();
            String path = String.valueOf(obj[6]);
            InputStream inputStream = new FileInputStream(path);
            byte[] imageToByteArray = IOUtils.toByteArray(inputStream);
            String img = byteArraytoBase64(imageToByteArray);
            inputStream.close();

            mp.put("appointmentTime", String.valueOf(obj[0]));
            mp.put("location",String.valueOf(obj[1]));
            mp.put("nickName", String.valueOf(obj[2]));
            mp.put("company", String.valueOf(obj[3]));
            mp.put("job", String.valueOf(obj[4]));
            mp.put("untact", String.valueOf(obj[5]));
            mp.put("profileImage", img);
            mapList.add(mp);
        }

        return mapList;

    }

    public void setMatching(MatchingDTO matching){
        matchingRepository.save(matching.toEntity());
        log.info("매칭 생성");
    }
    public void setReview(ReviewDTO review) {
        reviewRepository.save(review.toEntity());
        log.info("리뷰 생성");
    }
    public String getReviewAvg(Long mentoId) {
        List<Object[]> objects = reviewRepository.getReviewRating(mentoId);
        Object[] obj = objects.get(0);
        double rating = Double.parseDouble(String.valueOf(obj[1]));
        return String.format("%.2f", rating);
    }
    public String byteArraytoBase64(byte[] img) {
        String base64Img = Base64.getEncoder().encodeToString(img);
        return base64Img;
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
    public List<Object[]> setMessage(MessageDTO messageDTO) {
        messageRepository.save(messageDTO.toEntity());
        Matching matching = new Matching();
        matching.setId(messageDTO.getMatchingId());
        List<Object[]> messageList = messageRepository.findByMatching(matching);
        return messageList;

    }
}
