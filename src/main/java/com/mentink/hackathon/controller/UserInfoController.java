package com.mentink.hackathon.controller;

import com.mentink.hackathon.dto.MentoDTO;
import com.mentink.hackathon.dto.ProfileImageDTO;
import com.mentink.hackathon.dto.ShortProfileDTO;
import com.mentink.hackathon.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/app")
@RestController
public class UserInfoController {
    private final ProfileService profileService;

    //프로필 이름, 이미지
    @PostMapping("/user/profile/short/{userId}")
    public ShortProfileDTO getShortProfile(@PathVariable("userId") Long userId) throws IOException {
        ShortProfileDTO shortProfileDTO = profileService.getShortProfile(userId);
        log.info("user:"+userId+" take shortProfile");
        return shortProfileDTO;
    }
    //프로필 이미지 설정
    @PostMapping("/user/profileImg")
    public ResponseEntity setProfileImage(@ModelAttribute ProfileImageDTO profileImageDTO) throws IOException {
        profileService.setProfileImage(profileImageDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user/profileImg/{userId}")
    public ResponseEntity deleteProfileImage(@PathVariable("userId") Long userId) {
        profileService.deleteProfileImage(userId);
        log.info("user : "+ userId + " delete profile Image");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/mento/content")
    public Map<String, String> getJobInfo(@RequestBody Map<String, Long> mp) {
        Long userId = mp.get("userId");

        Map<String, String> content = profileService.getJobContent(userId);

        return content;
    }
    @GetMapping("/user/mento/register")
    public ResponseEntity addMento(@RequestParam("userId") Long userId, @RequestParam("content") String content,
                                   @RequestParam("preferredLocation") String preferredLocation,@RequestParam("untact") boolean untact ) {
        MentoDTO mentoDTO = new MentoDTO(userId, content, preferredLocation, untact);

        profileService.setMento(mentoDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
