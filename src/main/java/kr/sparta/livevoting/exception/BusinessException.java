package kr.sparta.livevoting.exception;

import lombok.Getter;

@Getter // 클래스의 필드에 대한 Getter 메서드를 자동으로 생성해준다.
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static BusinessException of(ErrorCode errorCode) {
        return new BusinessException(errorCode);
    }
}
