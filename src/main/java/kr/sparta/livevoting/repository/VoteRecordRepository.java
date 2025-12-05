package kr.sparta.livevoting.repository;

import kr.sparta.livevoting.entity.Candidate;
import kr.sparta.livevoting.entity.Users;
import kr.sparta.livevoting.entity.Vote;
import kr.sparta.livevoting.entity.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {
    int countByCandidate(Candidate candidate);
    Optional<VoteRecord> findByVoteAndUser(Vote vote, Users user);
    boolean existsByVoteAndUser(Vote vote, Users user);
}
