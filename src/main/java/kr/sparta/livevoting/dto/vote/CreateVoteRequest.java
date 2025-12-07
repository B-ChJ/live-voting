package kr.sparta.livevoting.dto.vote;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateVoteRequest {
    private String title;
    private List<String> candidates;
    private String authorId;

    public CreateVoteRequest(String title, List<String> candidates, String authorId) {
        this.title = title;
        this.candidates = candidates;
        this.authorId = authorId;
    }
}
