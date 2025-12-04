package kr.sparta.livevoting.dto.auth;

import kr.sparta.livevoting.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponse {
    private final Long id;
    private final String userId;
    private final String nickName;
    private final String role;

    public static SignUpResponse from(Users user) {
        return new SignUpResponse(user.getId(),
                user.getLoginId(),
                user.getNickname(),
                String.valueOf(user.getRole()));
    }
}
