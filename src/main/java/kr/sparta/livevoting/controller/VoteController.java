package kr.sparta.livevoting.controller;

import kr.sparta.livevoting.dto.vote.CreateVoteRequest;
import kr.sparta.livevoting.dto.vote.CreateVoteResponse;
import kr.sparta.livevoting.dto.vote.VoteDetailsResponse;
import kr.sparta.livevoting.dto.vote.VoteInfoResponse;
import kr.sparta.livevoting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<CreateVoteResponse> createVote(@RequestBody CreateVoteRequest request) {
        CreateVoteResponse response = voteService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VoteInfoResponse>> getVotes() {
        List<VoteInfoResponse> votes = voteService.getVotes();

        return ResponseEntity.status(HttpStatus.OK).body(votes);
    }

    @GetMapping("/{voteId}")
    public ResponseEntity<VoteDetailsResponse> getVoteDetails(@PathVariable Long voteId) {
        VoteDetailsResponse response = voteService.getVoteDetails(voteId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
