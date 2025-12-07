package kr.sparta.livevoting.dto.vote;

import kr.sparta.livevoting.entity.Vote;
import lombok.Getter;

@Getter
public class ClosedVoteResponse {

    private final Long id;
    private final String status;

    public ClosedVoteResponse(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public static ClosedVoteResponse from(Vote vote) {
        return new ClosedVoteResponse(vote.getId(), vote.getStatus().name());
    }
}
