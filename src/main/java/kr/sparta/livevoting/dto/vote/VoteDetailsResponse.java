package kr.sparta.livevoting.dto.vote;

import kr.sparta.livevoting.dto.candidate.CandidateInfoResponse;
import kr.sparta.livevoting.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class VoteDetailsResponse {
    private final Long id;
    private final String title;
    private final String status;
    private final String author;
    private final String authorId;
    private final LocalDateTime createdAt;

    private final List<CandidateInfoResponse> candidates;

    private final int totalVotes;

    public static VoteDetailsResponse from(Vote vote,
                                           List<CandidateInfoResponse> candidates,
                                           int totalVotes) {
        return new VoteDetailsResponse(vote.getId(),
                vote.getTitle(),
                vote.getStatus().name(),
                vote.getAuthor().getNickname(),
                vote.getAuthor().getLoginId(),
                vote.getCreatedAt(),
                candidates,
                totalVotes);
    }
}
