package com.mentink.hackathon.service;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.domain.MenteeRole;
import com.mentink.hackathon.domain.Profile;
import com.mentink.hackathon.domain.ProfileImage;
import com.mentink.hackathon.dto.MentoDTO;
import com.mentink.hackathon.dto.ProfileImageDTO;
import com.mentink.hackathon.dto.ShortProfileDTO;
import com.mentink.hackathon.repository.MenteeRepository;
import com.mentink.hackathon.repository.MentoRepository;
import com.mentink.hackathon.repository.ProfileImageRepository;
import com.mentink.hackathon.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileService {

    private final ProfileImageRepository profileImageRepository;
    private final ProfileRepository profileRepository;
    private final MenteeRepository menteeRepository;
    private final MentoRepository mentoRepository;

    public ShortProfileDTO getShortProfile(Long userId) throws IOException {
        ShortProfileDTO shortProfileDTO = new ShortProfileDTO();
        Optional<ProfileImage> profileImage = profileImageRepository.findByMenteeId(userId);
        Optional<Profile> profile = profileRepository.findByMenteeId(userId);

        String path = profileImage.get().getPath();
        // 이미지 반환
        InputStream inputStream = new FileInputStream(path);
        byte[] imageToByteArray = IOUtils.toByteArray(inputStream);
        inputStream.close();
        shortProfileDTO.setProfileImage(imageToByteArray);
        shortProfileDTO.setNickName(profile.get().getNickName());

        return shortProfileDTO;

    }

    public void setProfileImage(ProfileImageDTO profileImageDTO) throws IOException {
        MultipartFile file = profileImageDTO.getImage();
        double size = file.getSize() /1024;
        profileImageDTO.setFileSize(size);
        if(!file.isEmpty()) {
            String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
            String filePath = rootPath+"/mentink/profileImg/"+profileImageDTO.getUserId();
            File dest = new File(filePath);
            if(!dest.exists()) {
                dest.mkdirs();
            }
            file.transferTo(dest);
            profileImageDTO.setPath(filePath);

            profileImageRepository.save(profileImageDTO.toEntity());

        }

    }
    public Map<String, String> getJobContent(Long userId) {
        Map<String, String> mp = new HashMap<>();
        Optional<Profile> profile = profileRepository.findByMenteeId(userId);
        Optional<Mentee> mentee = menteeRepository.findById(userId);
        mp.put("company", profile.get().getCompany());
        mp.put("job", profile.get().getJob());
        mp.put("email", mentee.get().getEmail());

        return mp;
    }

    public void deleteProfileImage(Long userId) {
        profileImageRepository.deleteByMenteeId(userId);

    }
    public void setMento(MentoDTO mentoDTO) {
        mentoRepository.save(mentoDTO.toEntity());

    }

}
