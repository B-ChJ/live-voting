package kr.sparta.livevoting.exception;


import jakarta.servlet.http.HttpServletRequest;
import kr.sparta.livevoting.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException ex, HttpServletRequest request) {

        ErrorResponseDto errorResponse = new ErrorResponseDto(LocalDateTime.now(),
                ex.getErrorCode().getHttpStatus().value(),
                ex.getErrorCode().getHttpStatus().getReasonPhrase(),
                ex.getErrorCode().getHttpStatus().name(),
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(errorResponse);
    }
}
