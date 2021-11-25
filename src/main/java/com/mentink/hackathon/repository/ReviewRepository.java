package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r.id, r.content, r.rating, r.matching from Review r where r.mento.id= ?1 ")
    List<Object[]> findReviewByMentoId(Long mentoId);
}
