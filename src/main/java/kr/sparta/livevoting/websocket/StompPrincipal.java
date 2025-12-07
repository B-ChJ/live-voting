package kr.sparta.livevoting.websocket;

import java.security.Principal;

public class StompPrincipal implements Principal {
    private final Long userId;

    public StompPrincipal(Long userId) {
        this.userId = userId;
    }

    @Override // 상위 클래스, 또는 인터페이스의 메서들르 재정의한다.
    public String getName() {
        return String.valueOf(userId);
    }

    public Long getUserId() {
        return userId;
    }
}
