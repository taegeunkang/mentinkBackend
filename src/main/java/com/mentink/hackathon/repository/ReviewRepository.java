package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "select r.id, r.content, r.rating, r.matching_id, p.nick_name from review r join profile p on r.mentee_id = p.mentee_id where r.mento_id= ?1 ", nativeQuery = true)
    List<Object[]> findReviewByMentoId(Long mentoId);
    @Query(value = "select mento_id, avg(rating) from review where mento_id = ?1 group by mento_id", nativeQuery = true)
    List<Object[]> getReviewRating(Long mentoId);

    @Query(value ="select r.mento_id, avg(r.rating) as rating, m.preferred_location, m.content, m.untact, m.mentee_id from review r join mento m on m.id = r.mento_id group by mento_id order by rating desc;" , nativeQuery = true)
    List<Object []> getMentoesOrderByRating();
}
