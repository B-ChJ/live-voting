package kr.sparta.livevoting.repository;

import kr.sparta.livevoting.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
