package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Mento;
import com.mentink.hackathon.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MentoRepository extends JpaRepository<Mento, Long> {
    Optional<Mento> findByMenteeId(Long mentee_id);
    @Query("select m.id, m.content, m.preferredLocation, m.untact, m.mentee.id from Mento m")
    List<Object[]> findMentoBy();
}
