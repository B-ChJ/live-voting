package kr.sparta.livevoting.dto.vote;

import kr.sparta.livevoting.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VoteInfoResponse {
    private final Long id;
    private final String title;
    private final String status;
    private final String author;
    private final String authorId;
    private final LocalDateTime createdAt;

    public static VoteInfoResponse from(Vote vote) {
        return new VoteInfoResponse(vote.getId(),
                vote.getTitle(),
                vote.getStatus().name(),
                vote.getAuthor().getNickname(),
                vote.getAuthor().getLoginId(),
                vote.getCreatedAt());
    }
}
