package kr.sparta.livevoting.controller;

import kr.sparta.livevoting.dto.vote.VoteInfoResponse;
import kr.sparta.livevoting.dto.vote.VoteRequest;
import kr.sparta.livevoting.dto.vote.VoteResponse;
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
    public ResponseEntity<VoteResponse> createVote(@RequestBody VoteRequest request) {
        VoteResponse response = voteService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VoteInfoResponse>> getVotes() {
        List<VoteInfoResponse> votes = voteService.getVotes();

        return ResponseEntity.status(HttpStatus.OK).body(votes);
    }
}
