package com.mentink.hackathon.service;

import com.mentink.hackathon.domain.community.Board;
import com.mentink.hackathon.domain.community.BoardImage;
import com.mentink.hackathon.domain.community.Comment;
import com.mentink.hackathon.dto.BoardDTO;
import com.mentink.hackathon.dto.CommentDTO;
import com.mentink.hackathon.repository.BoardImageRepository;
import com.mentink.hackathon.repository.BoardRepository;
import com.mentink.hackathon.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class CommunityService {
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;
    private final CommentRepository commentRepository;

    public void write(BoardDTO boardDTO) throws IOException {
        log.info(boardDTO.getWriterId()+"커뮤니티 글 작성 ");
        Board board = boardDTO.toEntity();
        Optional<Board> board1 = Optional.of(boardRepository.save(board));
        Long boardId = board1.get().getId();
        List<MultipartFile> images  = boardDTO.getImages();
        saveImage(images, boardId);

    }
    public void saveImage(List<MultipartFile> images, Long boardId) throws IOException {
        int cnt = 0;
        Board board = new Board();
        board.setId(boardId);
        for(MultipartFile image : images) {
            double size = image.getSize() / 1024;
            if (!image.isEmpty()) {
                String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
                String filePath = rootPath + "/mentink/board/"+boardId+"/image"+cnt;
                File dest = new File(filePath);
                if (!dest.exists()) {
                    dest.mkdirs();
                }
                image.transferTo(dest);
                boardImageRepository.save(BoardImage.builder().board(board)
                        .filesize(size)
                        .path(filePath)
                        .build());
                cnt++;


            }

        }

    }

    public void commentWrite(CommentDTO comementDTO) {
        Comment comment = comementDTO.toEntity();
        commentRepository.save(comment);

    }
}
