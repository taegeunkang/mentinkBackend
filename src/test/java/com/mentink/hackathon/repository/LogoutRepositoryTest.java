package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Logout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class LogoutRepositoryTest {

    @Autowired
    private LogoutRepository logoutRepository;

    @Test
    public void 테스트( ){
        Optional<Logout> logout = logoutRepository.findByToken("eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc0MDMwNzUsImV4cCI6MTYzNzQ4OTQ3NSwic3ViIjoiZGVhbmthbmcifQ.91QF4sGXWbGpp9bzWHHiccjIcdsRtQQdOJyQsGKYm_U");
        logout.ifPresent(a -> {
            System.out.println(a.getToken());
            System.out.println(a.getExpireDate());
        });
    }

}