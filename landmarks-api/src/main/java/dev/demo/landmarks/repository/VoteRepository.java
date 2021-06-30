package dev.demo.landmarks.repository;

import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {
}
