package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByMenteeId(Long user_id);
    @Query("select p.nickName, i.path, p.verified, p.job, p.year  from Profile p join ProfileImage i on p.mentee = i.mentee where p.mentee.id = ?1")
    List<Object[]> findProfileInfoShort(Long user_id);
    @Modifying
    @Query(value = "update Profile p set p.verified = true where p.mentee.id = ?1")
    void updateVerified(Long userId);
}
