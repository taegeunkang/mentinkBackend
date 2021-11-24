package com.mentink.hackathon.service;

import com.mentink.hackathon.domain.*;
import com.mentink.hackathon.dto.MenteeJobInfoDTO;
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
import java.util.List;
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
        List<Object[] > objects = profileRepository.findProfileInfoShort(userId);
        Object[] object = objects.get(0);
        String nickname = String.valueOf(object[0]);
        String path = String.valueOf(object[1]);
        Optional<Mento> mento = mentoRepository.findByMenteeId(userId);
        boolean verified = false;

        if(mento.isPresent())
            verified = true;

        // 이미지 반환
        InputStream inputStream = new FileInputStream(path);
        byte[] imageToByteArray = IOUtils.toByteArray(inputStream);
        inputStream.close();
        ShortProfileDTO shortProfileDTO = ShortProfileDTO.builder().profileImage(imageToByteArray)
                .nickName(nickname)
                .verified(verified).build();

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
        List<Object[]> ob = menteeRepository.findByUserId(userId);
        Object[] object = ob.get(0);
        String email = String.valueOf(object[0]);
        String company = (object[1] != null) ? String.valueOf(object[1]): null;
        String job = (object[2] != null) ? String.valueOf(object[2]) : null;
        mp.put("email", email);
        mp.put("company", company);
        mp.put("job", job);

        return mp;
    }

    public void deleteProfileImage(Long userId) {
        profileImageRepository.deleteByMenteeId(userId);

    }
    public void setMento(MentoDTO mentoDTO) {
        Mento mt = mentoDTO.toEntity();
        mentoRepository.save(mt);

    }

}
