package kr.sparta.livevoting.service;

import kr.sparta.livevoting.dto.candidate.CandidateInfoResponse;
import kr.sparta.livevoting.entity.Candidate;
import kr.sparta.livevoting.exception.BusinessException;
import kr.sparta.livevoting.exception.ErrorCode;
import kr.sparta.livevoting.repository.CandidateRepository;
import kr.sparta.livevoting.repository.VoteRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final VoteRecordRepository voteRecordRepository;

    public CandidateService(CandidateRepository candidateRepository,
                            VoteRecordRepository voteRecordRepository) {
        this.candidateRepository = candidateRepository;
        this.voteRecordRepository = voteRecordRepository;
    }

    public CandidateInfoResponse countVotes(Candidate candidate) {
        return CandidateInfoResponse.from(candidate,
                voteRecordRepository.countByCandidate(candidate));
    }

    public Candidate findCandidate(long id) {
        return candidateRepository.findById(id).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
