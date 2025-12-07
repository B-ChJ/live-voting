package kr.sparta.livevoting.exception;


import jakarta.servlet.http.HttpServletRequest;
import kr.sparta.livevoting.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice // 예외 처리와 응답을 전역적으로 관리할 수 있도록 해준다.
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class) // 컨트롤러에서 발생하는 예외(BusinessException 등)를 처리해준다.
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
