package com.mentink.hackathon.service;

import com.mentink.hackathon.domain.*;
import com.mentink.hackathon.dto.MenteeJobInfoDTO;
import com.mentink.hackathon.dto.MentoDTO;
import com.mentink.hackathon.dto.ProfileImageDTO;
import com.mentink.hackathon.dto.ShortProfileDTO;
import com.mentink.hackathon.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.*;


@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileService {

    private final ProfileImageRepository profileImageRepository;
    private final ProfileRepository profileRepository;
    private final ReviewRepository reviewRepository;
    private final MenteeRepository menteeRepository;
    private final MentoRepository mentoRepository;

    private final AppService appService;

    public ShortProfileDTO getShortProfile(Long userId) throws IOException {
        List<Object[] > objects = profileRepository.findProfileInfoShort(userId);
        Object[] object = objects.get(0);
        String nickname = String.valueOf(object[0]);
        String path = String.valueOf(object[1]);
        boolean verified = (boolean)object[2];
        // 이미지 반환
        InputStream inputStream = new FileInputStream(path);
        byte[] imageToByteArray = IOUtils.toByteArray(inputStream);
        String img = appService.byteArraytoBase64(imageToByteArray);
        inputStream.close();
        ShortProfileDTO shortProfileDTO = ShortProfileDTO.builder().profileImage(img)
                .nickName(nickname)
                .verified(verified).build();

        return shortProfileDTO;

    }

    public List<Map<String, String>> getRecommendMentores() {
        List<Map<String, String>> mapList =new ArrayList<>();
        List<Object []> objects = reviewRepository.getMentoesOrderByRating();
        objects.stream().forEach(objects1 -> {
            Map<String, String> mp = new HashMap<>();
            Long userId = Long.parseLong(String.valueOf(objects1[5]));
            List<Object[]> objects2 = profileRepository.findProfileInfoShort(userId);
            Object[] profile = objects2.get(0);
            String nickName = String.valueOf(profile[0]);
            String path = String.valueOf(profile[1]);
            String job = String.valueOf(profile[3]);
            String year = String.valueOf(profile[4]);
            String img = "";
            try {
                InputStream inputStream = new FileInputStream(path);
                byte[] imageToByteArray = IOUtils.toByteArray(inputStream);
                img = appService.byteArraytoBase64(imageToByteArray);
                inputStream.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

            mp.put("mentoId", String.valueOf(objects1[0]));
            mp.put("rating", String.format("%.2f", Double.parseDouble(String.valueOf(objects1[1]))));
            mp.put("preferredLocation", String.valueOf(objects1[2]));
            mp.put("content", String.valueOf(objects1[3]));
            mp.put("untact", String.valueOf(objects1[4]));
            mp.put("nickName", nickName);
            mp.put("job", job);
            mp.put("year", year);
            mp.put("profileImage", img);
            mapList.add(mp);
        });

        return mapList;
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
    @Transactional
    public void setVerified(Long userId) {
        profileRepository.updateVerified(userId);
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
