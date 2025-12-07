package kr.sparta.livevoting.jwt;

import kr.sparta.livevoting.entity.Users;
import kr.sparta.livevoting.exception.BusinessException;
import kr.sparta.livevoting.exception.ErrorCode;
import kr.sparta.livevoting.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 해당 클래스가 Component 중 Service 역할임을 명시해준다.
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override // 상위 클래스, 또는 인터페이스의 메서드를 재정의한다.
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Users user = userRepository.findByLoginId(loginId).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        //사용자 정보를 UserDetails 객체로 변환
        return new CustomUserDetails(user);
    }
}
