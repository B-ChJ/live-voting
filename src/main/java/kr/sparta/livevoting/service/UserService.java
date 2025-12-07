package kr.sparta.livevoting.service;

import jakarta.transaction.Transactional;
import kr.sparta.livevoting.dto.UserResponseDto;
import kr.sparta.livevoting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // 해당 클래스가 Component 중 Service 역할임을 명시해준다.
@RequiredArgsConstructor // 필수 필드(final, @NotNull 등)의 생성자를 자동으로 만들어준다.
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }
}
