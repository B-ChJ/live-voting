package kr.sparta.livevoting.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    TOKEN_EXPIRED_OR_INVALID(HttpStatus.UNAUTHORIZED, "유효하지 않거나 만료된 토큰입니다."), // 401
    FORBIDDEN_AUTHOR_ONLY(HttpStatus.FORBIDDEN, "해당 기능을 수행할 수 있는 권한이 없습니다."), // 403
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "요청을 찾을 수 없습니다."), // 404
    CONFLICT(HttpStatus.CONFLICT, "이미 등록된 ID가 있어 사용할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
