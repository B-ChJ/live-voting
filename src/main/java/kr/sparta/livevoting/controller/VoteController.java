package kr.sparta.livevoting.controller;

import kr.sparta.livevoting.dto.vote.*;
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

    @PostMapping("/{voteId}/vote")
    public ResponseEntity<VotedResponse> vote(@PathVariable Long voteId, @RequestBody VoteToRequest request) {

        VotedResponse response = voteService.voteTo(voteId, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
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

    @GetMapping("/{voteId}/vote-record")
    public ResponseEntity<VotedResponse> getVoteRecord(@PathVariable Long voteId, @RequestParam String voterId) {

        VotedResponse response = voteService.getRecord(voteId, voterId);

        if(response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{voteId}/close")
    public ResponseEntity<ClosedVoteResponse> closeVote(@PathVariable Long voteId,
                                                        @RequestBody CloseVoteRequest request) {

        ClosedVoteResponse response = voteService.close(voteId, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
