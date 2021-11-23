package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage ,Long > {

    void deleteByMenteeId(Long mentee_id);
    Optional<ProfileImage> findByMenteeId(Long mentee_id);
}
