package kr.sparta.livevoting.dto.vote;

import kr.sparta.livevoting.entity.VoteRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VotedResponse {
    private final Long voteId;
    private final Long candidateId;
    private final String voterId;
    private final LocalDateTime votedAt;

    public static VotedResponse from(VoteRecord record) {
        return new VotedResponse(record.getVote().getId(),
                record.getCandidate().getId(),
                record.getUser().getLoginId(),
                record.getCreatedAt());
    }
}
