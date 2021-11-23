package com.mentink.hackathon.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/verify/email") // 이메일 인증 코드 보내기
    public ResponseEntity emailAuth(@RequestBody Map<String, String> email) throws Exception {
        emailService.sendSimpleMessage(email.get("email"));
        log.info("이메일 인증 코드 발송 요청");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/verify/verifycode") // 이메일 인증 코드 검증
    public ResponseEntity verifyCode(@RequestBody Map<String, String> code) {
        if(EmailService.ePw.equals(code.get("code"))) {
            log.info("코드 인증 성공");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else{
            log.warn("코드 인증 실패");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}