package kr.sparta.livevoting.dto.vote;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VoteToRequest {
    private Long candidateId;
    private String voterId;

    public VoteToRequest(Long candidateId, String voterId) {
        this.candidateId = candidateId;
        this.voterId = voterId;
    }
}
