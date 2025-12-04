package kr.sparta.livevoting.service;

import kr.sparta.livevoting.dto.auth.SignUpRequest;
import kr.sparta.livevoting.dto.auth.SignUpResponse;
import kr.sparta.livevoting.entity.Users;
import kr.sparta.livevoting.exception.BusinessException;
import kr.sparta.livevoting.exception.ErrorCode;
import kr.sparta.livevoting.jwt.JwtUtil;
import kr.sparta.livevoting.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public SignUpResponse register(SignUpRequest request) {
        if(userRepository.existsByLoginId(request.getLoginId())) {
            throw new BusinessException(ErrorCode.CONFLICT);
        }

        String passwordHash = passwordEncoder.encode(request.getPassword());

        Users user = new Users(request.getLoginId(), passwordHash, request.getNickName());
        Users savedUser = userRepository.save(user);

        return SignUpResponse.from(savedUser);
    }
}
