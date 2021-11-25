package com.mentink.hackathon.controller;


import com.mentink.hackathon.dto.*;
import com.mentink.hackathon.security.util.JWTUtil;
import com.mentink.hackathon.service.LoginService;
import com.mentink.hackathon.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final LoginService loginService;
    private final ProfileService profileService;
    private final JWTUtil jwtUtil;

    @GetMapping("/user/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
        //필터에서 처리
    }

    @GetMapping("/user/logout")
    public ResponseEntity<String> logout(@RequestParam("token") String token, @RequestParam("refreshToken") String refreshToken) {
        Date tokenExp = jwtUtil.getExp(token);
        Date refreshTokenExp = jwtUtil.getExp(refreshToken);
        loginService.logout(token, refreshToken, tokenExp, refreshTokenExp);
        return new ResponseEntity("Logout!",HttpStatus.NO_CONTENT);
    }

    //토큰 갱신
    @PostMapping("/user/refresh")
    public ResponseEntity<String> refreshLogin(@RequestBody RefreshTokenDTO refreshTokenDto) throws Exception {
        String content = jwtUtil.validateAndExtract(refreshTokenDto.getRefreshToken());
        if(content != null && content.equals(refreshTokenDto.getUsername())){
            String newOne = jwtUtil.refresh(content);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.    setContentType(new MediaType(MediaType.TEXT_PLAIN));
            httpHeaders.set("token", newOne);

            return new ResponseEntity<>("issued", httpHeaders, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/user/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody HashMap<String, String> body) {

        String userName = body.get("username");
        String password = body.get("password");
        String email = body.get("email");
        String nickName = body.get("nickname");
        String gender = body.get("gender");
        String birth = body.get("birth");
        String company = body.get("company");
        String job = body.get("job");
        Integer year = Integer.parseInt(body.get("year"));
        String school = body.get("school");
        String major = body.get("major");

        MenteeDTO menteeDTO = new MenteeDTO(userName, password, email);
        ProfileDTO profileDTO = new ProfileDTO(nickName, gender,birth ,company, job,year ,school, major);

        loginService.signup(menteeDTO, profileDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }




}
