package kr.sparta.livevoting.service;

import kr.sparta.livevoting.dto.auth.LoginRequest;
import kr.sparta.livevoting.dto.auth.SignUpRequest;
import kr.sparta.livevoting.dto.auth.SignUpResponse;
import kr.sparta.livevoting.dto.auth.TokenResponse;
import kr.sparta.livevoting.entity.Users;
import kr.sparta.livevoting.exception.BusinessException;
import kr.sparta.livevoting.exception.ErrorCode;
import kr.sparta.livevoting.jwt.CustomUserDetails;
import kr.sparta.livevoting.jwt.JwtUtil;
import kr.sparta.livevoting.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    public TokenResponse login(LoginRequest request) {
        Users user = userRepository.findByLoginId(request.getLoginId()).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.FORBIDDEN_INVALID_ACCESS);
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                "",
                userDetails.getAuthorities());

        String accessToken = jwtUtil.createAccessToken(authentication);
        String refreshToken = jwtUtil.createRefreshToken(authentication);

        return new TokenResponse(accessToken, refreshToken);

    }
}
