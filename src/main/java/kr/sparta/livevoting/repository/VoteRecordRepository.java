package kr.sparta.livevoting.repository;

import kr.sparta.livevoting.entity.Candidate;
import kr.sparta.livevoting.entity.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {
    int countByCandidate(Candidate candidate);
}
