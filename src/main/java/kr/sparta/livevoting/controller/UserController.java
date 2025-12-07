package kr.sparta.livevoting.controller;

import kr.sparta.livevoting.dto.UserResponseDto;
import kr.sparta.livevoting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // HTTP 요청과 응답을 처리·반환하는 컨트롤러임을 나타냄 = (@Controller + @ResponseBody)
// REST API를 제공한다.
@RequiredArgsConstructor // 필수 필드(final, @NotNull 등)의 생성자를 자동으로 만들어준다.
@RequestMapping("/api/members") // HTTP 요청을 각 컨트롤러 메서드와 연결(Mapping)해준다. (기본 경로 설정)
public class UserController {

    private final UserService userService;

    /**
     * 사용자 목록 조회
     * @return
     */
    @GetMapping // HTTP GET 요청을 지정 경로와 연결해준다.
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
