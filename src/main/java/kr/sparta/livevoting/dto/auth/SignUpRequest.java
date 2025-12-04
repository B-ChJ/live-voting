package kr.sparta.livevoting.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String loginId;
    private String password;
    private String nickName;

    public SignUpRequest(String loginId, String password, String nickName) {
        this.loginId = loginId;
        this.password = password;
        this.nickName = nickName;
    }
}
