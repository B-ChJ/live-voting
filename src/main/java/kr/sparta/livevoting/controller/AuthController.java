package kr.sparta.livevoting.controller;

import kr.sparta.livevoting.dto.CommonResponse;
import kr.sparta.livevoting.dto.auth.SignUpRequest;
import kr.sparta.livevoting.dto.auth.SignUpResponse;
import kr.sparta.livevoting.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<SignUpResponse>> signup(@RequestBody SignUpRequest request) {
        SignUpResponse response = authService.register(request);

        CommonResponse<SignUpResponse> body = CommonResponse.<SignUpResponse>builder()
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
