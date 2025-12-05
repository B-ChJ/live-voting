package kr.sparta.livevoting.dto.candidate;

import kr.sparta.livevoting.entity.Candidate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CandidateInfoResponse {
    private final Long id;
    private final String name;
    private final int voteCount;

    public static CandidateInfoResponse from(Candidate candidate, int voteCount) {
        return new CandidateInfoResponse(candidate.getId(),
                candidate.getName(),
                voteCount);
    }
}
