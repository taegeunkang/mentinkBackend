package com.mentink.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MeninKApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeninKApplication.class, args);
    }

}
