package com.mentink.hackathon.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.dto.MenteeDTO;
import com.mentink.hackathon.dto.ProfileDTO;
import com.mentink.hackathon.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;


@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final LoginService loginService;

    @GetMapping("/user/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {

    }

    @PostMapping(value = "/user/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody HashMap<String, String> body) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String userName = body.get("username");
        String password = body.get("password");
        String email = body.get("email");
        String nickName = body.get("nickName");
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


        return new ResponseEntity(HttpStatus.OK);

    }

}
