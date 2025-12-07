package kr.sparta.livevoting.controller;

import kr.sparta.livevoting.dto.auth.LoginRequest;
import kr.sparta.livevoting.dto.auth.SignUpRequest;
import kr.sparta.livevoting.dto.auth.SignUpResponse;
import kr.sparta.livevoting.dto.auth.TokenResponse;
import kr.sparta.livevoting.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // HTTP 요청과 응답을 처리·반환하는 컨트롤러임을 나타냄 = (@Controller + @ResponseBody)
// REST API를 제공한다.
@RequestMapping("/api/auth") // HTTP 요청을 각 컨트롤러 메서드와 연결(Mapping)해준다. (기본 경로 설정)
@RequiredArgsConstructor // 필수 필드(final, @NotNull 등)의 생성자를 자동으로 만들어준다.
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup") // HTTP POST 요청을 지정 경로와 연결해준다.
    public ResponseEntity<SignUpResponse> signup(@RequestBody SignUpRequest request) {
        SignUpResponse response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        TokenResponse token = authService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
