package kr.sparta.livevoting.service;

import kr.sparta.livevoting.dto.candidate.CandidateInfoResponse;
import kr.sparta.livevoting.dto.vote.CreateVoteRequest;
import kr.sparta.livevoting.dto.vote.CreateVoteResponse;
import kr.sparta.livevoting.dto.vote.VoteDetailsResponse;
import kr.sparta.livevoting.dto.vote.VoteInfoResponse;
import kr.sparta.livevoting.entity.Candidate;
import kr.sparta.livevoting.entity.Users;
import kr.sparta.livevoting.entity.Vote;
import kr.sparta.livevoting.exception.BusinessException;
import kr.sparta.livevoting.exception.ErrorCode;
import kr.sparta.livevoting.repository.UserRepository;
import kr.sparta.livevoting.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final CandidateService candidateService;

    public VoteService(VoteRepository voteRepository,
                       UserRepository userRepository,
                       CandidateService candidateService) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.candidateService = candidateService;
    }

    public CreateVoteResponse create(CreateVoteRequest request) {

        Users author = userRepository.findByLoginId(request.getAuthorId()).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Vote vote = new Vote(request.getTitle());
        vote.setAuthor(author);

        List<Candidate> candidateList = request.getCandidates().stream()
                .map(name -> new Candidate(name, vote))
                .toList();

        vote.setCandidateList(candidateList);

        Vote savedVote = voteRepository.save(vote);

        return new CreateVoteResponse(savedVote.getId());
    }

    public List<VoteInfoResponse> getVotes() {
        List<Vote> votes = voteRepository.findAll();

        return votes.stream()
                .map(VoteInfoResponse::from)
                .toList();
    }

    public VoteDetailsResponse getVoteDetails(Long voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        List<Candidate> candidateList = vote.getCandidateList();
        List<CandidateInfoResponse> candidates = candidateList.stream()
                .map(candidateService::countVotes)
                .toList();

        int totalVotes = vote.getVoteRecordList().size();

        return VoteDetailsResponse.from(vote, candidates, totalVotes);
    }
}
