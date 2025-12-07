package kr.sparta.livevoting.service;

import kr.sparta.livevoting.dto.candidate.CandidateInfoResponse;
import kr.sparta.livevoting.dto.vote.*;
import kr.sparta.livevoting.entity.Candidate;
import kr.sparta.livevoting.entity.Users;
import kr.sparta.livevoting.entity.Vote;
import kr.sparta.livevoting.entity.VoteRecord;
import kr.sparta.livevoting.exception.BusinessException;
import kr.sparta.livevoting.exception.ErrorCode;
import kr.sparta.livevoting.repository.UserRepository;
import kr.sparta.livevoting.repository.VoteRecordRepository;
import kr.sparta.livevoting.repository.VoteRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final CandidateService candidateService;
    private final VoteRecordRepository voteRecordRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public VoteService(VoteRepository voteRepository,
                       UserRepository userRepository,
                       CandidateService candidateService, VoteRecordRepository voteRecordRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.candidateService = candidateService;
        this.voteRecordRepository = voteRecordRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public CreateVoteResponse create(CreateVoteRequest request) {

        Users author = findUser(request.getAuthorId());

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
        Vote vote = findVote(voteId);

        List<Candidate> candidateList = vote.getCandidateList();
        List<CandidateInfoResponse> candidates = candidateList.stream()
                .map(candidateService::countVotes)
                .toList();

        int totalVotes = vote.getVoteRecordList().size();

        return VoteDetailsResponse.from(vote, candidates, totalVotes);
    }

    public VotedResponse voteTo(Long voteId, VoteToRequest request) {

        Vote vote = findVote(voteId);

        Users user = findUser(request.getVoterId());

        Candidate candidate = candidateService.findCandidate(request.getCandidateId());

        if(voteRecordRepository.existsByVoteAndUser(vote, user)) {
            VoteRecord updateVoteRecord = voteRecordRepository.findByVoteAndUser(vote, user).orElseThrow(
                    () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

            updateVoteRecord.setCandidate(candidate);
            voteRecordRepository.save(updateVoteRecord);

            String message = user.getNickname() + "님이 " + vote.getTitle() + " 투표에 다시 참여하셨습니다.";
            simpMessagingTemplate.convertAndSend("/topic/announcements", message);
            System.out.println(message);

            return VotedResponse.from(updateVoteRecord);
        }

        VoteRecord voteRecord = new VoteRecord(user, vote, candidate);
        VoteRecord savedRecord = voteRecordRepository.save(voteRecord);

        String message = user.getNickname() + "님이 " + vote.getTitle() + " 투표에 참여하셨습니다.";
        simpMessagingTemplate.convertAndSend("/topic/announcements", message);

        return VotedResponse.from(savedRecord);
    }

    public VotedResponse getRecord(Long voteId, String voterId) {
        Vote vote = findVote(voteId);
        Users user = findUser(voterId);

        VoteRecord record = voteRecordRepository.findByVoteAndUser(vote, user).orElse(null);

        if(record == null) {return null;}

        return VotedResponse.from(record);
    }

    public ClosedVoteResponse close(Long voteId, CloseVoteRequest request) {
        Vote vote = findVote(voteId);

        if(!request.getAuthorId().equals(vote.getAuthor().getLoginId())) {
            throw new BusinessException(ErrorCode.FORBIDDEN_AUTHOR_ONLY);
        }

        vote.setStatus(Vote.VoteStatus.CLOSED);
        voteRepository.save(vote);

        return ClosedVoteResponse.from(vote);
    }

    private Users findUser(String loginId) {
        return userRepository.findByLoginId(loginId).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    private Vote findVote(Long voteId) {
        return voteRepository.findById(voteId).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
