package kr.sparta.livevoting.dto.vote;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CloseVoteRequest {

    private String authorId;

    public CloseVoteRequest(String authorId) {
        this.authorId = authorId;
    }
}
