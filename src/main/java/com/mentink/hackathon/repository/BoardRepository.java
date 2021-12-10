package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.community.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "select b.id, b.writer_id, b.created_date_time, b.content, b.image_cnt, c.comment_cnt" +
            " from (select b.id, b.writer_id, b.created_date_time, b.content, i.cnt as image_cnt from board b" +
            " left join (select board_id, count(id) as cnt from board_image group by board_id) as i " +
            "on b.id = i.board_id) as b left join (select board_id, count(id) as comment_cnt from comment " +
            "group by board_id) as c on c.board_id = b.id;", nativeQuery = true)
    List<Object[]> findBoardsWithImageCntAndCommentCnt();
}
