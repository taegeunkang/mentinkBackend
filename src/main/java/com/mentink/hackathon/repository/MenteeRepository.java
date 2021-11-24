package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Mentee;
import com.mentink.hackathon.dto.MenteeJobInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    Mentee findByUserName(String username);
    @Query(value = "select m.email, p.company as company, p.job as job from Mentee m join Profile p on m = p.mentee where m.id = ?1")
    List<Object[]> findByUserId(Long userId);
}
