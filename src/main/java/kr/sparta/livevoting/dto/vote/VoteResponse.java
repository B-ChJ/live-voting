package kr.sparta.livevoting.dto.vote;

import lombok.Getter;

@Getter
public class VoteResponse {
    private final Long id;

    public VoteResponse(Long id) {
        this.id = id;
    }
}
