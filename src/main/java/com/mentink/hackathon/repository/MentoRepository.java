package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Mento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoRepository extends JpaRepository<Mento, Long> {
}
