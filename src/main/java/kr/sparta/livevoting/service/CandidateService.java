package kr.sparta.livevoting.service;

import kr.sparta.livevoting.dto.candidate.CandidateInfoResponse;
import kr.sparta.livevoting.entity.Candidate;
import kr.sparta.livevoting.repository.VoteRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    private final VoteRecordRepository voteRecordRepository;

    public CandidateService(VoteRecordRepository voteRecordRepository) {
        this.voteRecordRepository = voteRecordRepository;
    }

    public CandidateInfoResponse countVotes(Candidate candidate) {
        return new CandidateInfoResponse(candidate.getId(),
                candidate.getName(),
                voteRecordRepository.countByCandidate(candidate));
    }
}
