package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {
    @Query(value = "select m.appointment_time, m.location, e.nick_name, e.company, e.job, e.untact, e.path " +
            "from matching m join (select e.mento_id, e.nick_name, e.company, e.job, e.untact, i.path " +
            "from (select p.id as mento_id,m.mentee_id, m.nick_name, m.company, m.job, p.untact from profile m " +
            "join mento p on m.mentee_id = p.mentee_id) as e join profile_image i on i.mentee_id = e.mentee_id) as e " +
            "on m.mento_id = e.mento_id where m.mentee_id = ?1 and m.appointment_time > now()", nativeQuery = true)
    List<Object[]> findSchedule(Long menteeId);
}
