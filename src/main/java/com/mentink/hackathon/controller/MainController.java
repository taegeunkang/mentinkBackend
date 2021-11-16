package com.mentink.hackathon.controller;

import com.mentink.hackathon.repository.MenteeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {

    @GetMapping("/main")
    public String hello() {
        log.info("requested");

        return "hi";
    }


    @GetMapping("/test")
    public String test() {
        return "login successed";
    }

}
