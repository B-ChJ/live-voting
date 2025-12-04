package kr.sparta.livevoting.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDto {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String errCode;
    private final String message;
    private final String path;

    public ErrorResponseDto(LocalDateTime timestamp, int status,
                            String error, String errCode,
                            String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.errCode = errCode;
        this.message = message;
        this.path = path;
    }
}
