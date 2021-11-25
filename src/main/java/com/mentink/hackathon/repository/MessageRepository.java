package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Matching;
import com.mentink.hackathon.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m.id, m.content, m.from, m.to, m.createdDateTime from Message m where m.matching = ?1")
    List<Object[]> findByMatching(Matching matching);
}
