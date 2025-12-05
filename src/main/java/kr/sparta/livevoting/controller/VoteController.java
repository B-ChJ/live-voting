package kr.sparta.livevoting.controller;

import kr.sparta.livevoting.dto.CommonResponse;
import kr.sparta.livevoting.dto.vote.VoteRequest;
import kr.sparta.livevoting.dto.vote.VoteResponse;
import kr.sparta.livevoting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<CommonResponse<VoteResponse>> createVote(@RequestBody VoteRequest request) {
        VoteResponse response = voteService.create(request);

        CommonResponse<VoteResponse> body = CommonResponse.<VoteResponse>builder()
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
