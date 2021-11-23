package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.ProfileImage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProfileImageRepositoryTest {

    @Autowired
    private ProfileImageRepository profileImageRepository;
    @Test
    public void test() {
        Optional<ProfileImage> profileImage = profileImageRepository.findByMenteeId(1L);
        profileImage.ifPresent(img -> {
            System.out.println(img.getPath());
            System.out.println(img.getFilesize());
            System.out.println(img.getMentee().getId());
        });
    }

}