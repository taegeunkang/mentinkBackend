package com.mentink.hackathon.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void 테스트트트로트() {
        List<Object []> objects = boardRepository.findBoardsWithImageCntAndCommentCnt();
        objects.stream().forEach( board -> {
            System.out.println(board[3]);
        });
    }

}