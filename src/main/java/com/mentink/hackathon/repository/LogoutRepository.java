package com.mentink.hackathon.repository;

import com.mentink.hackathon.domain.Logout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogoutRepository extends JpaRepository<Logout, Long> {

    Optional<Logout> findByToken(String token);
}
