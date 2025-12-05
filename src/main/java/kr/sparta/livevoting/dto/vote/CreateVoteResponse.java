package kr.sparta.livevoting.dto.vote;

import lombok.Getter;

@Getter
public class CreateVoteResponse {
    private final Long id;

    public CreateVoteResponse(Long id) {
        this.id = id;
    }
}
