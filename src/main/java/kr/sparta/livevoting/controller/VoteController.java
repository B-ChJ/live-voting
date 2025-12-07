package kr.sparta.livevoting.controller;

import kr.sparta.livevoting.dto.vote.*;
import kr.sparta.livevoting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // HTTP 요청과 응답을 처리·반환하는 컨트롤러임을 나타냄 = (@Controller + @ResponseBody)
// REST API를 제공한다.
@RequestMapping("/api/votes") // HTTP 요청을 각 컨트롤러 메서드와 연결(Mapping)해준다. (기본 경로 설정)
@RequiredArgsConstructor // 필수 필드(final, @NotNull 등)의 생성자를 자동으로 만들어준다.
public class VoteController {
    private final VoteService voteService;

    @PostMapping // HTTP POST 요청을 지정 경로와 연결해준다.
    public ResponseEntity<CreateVoteResponse> createVote(@RequestBody CreateVoteRequest request) {

        CreateVoteResponse response = voteService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{voteId}/vote")
    public ResponseEntity<VotedResponse> vote(@PathVariable Long voteId, @RequestBody VoteToRequest request) {

        VotedResponse response = voteService.voteTo(voteId, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping // HTTP GET 요청을 지정 경로와 연결해준다.
    public ResponseEntity<List<VoteInfoResponse>> getVotes() {

        List<VoteInfoResponse> votes = voteService.getVotes();

        return ResponseEntity.status(HttpStatus.OK).body(votes);
    }

    @GetMapping("/{voteId}")
    public ResponseEntity<VoteDetailsResponse> getVoteDetails(@PathVariable Long voteId) {

        VoteDetailsResponse response = voteService.getVoteDetails(voteId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{voteId}/vote-record")
    public ResponseEntity<VotedResponse> getVoteRecord(@PathVariable Long voteId, @RequestParam String voterId) {

        VotedResponse response = voteService.getRecord(voteId, voterId);

        if(response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{voteId}/close") // HTTP PATCH 요청을 지정 경로와 연결해준다.
    public ResponseEntity<ClosedVoteResponse> closeVote(@PathVariable Long voteId,
                                                        @RequestBody CloseVoteRequest request) {

        ClosedVoteResponse response = voteService.close(voteId, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
