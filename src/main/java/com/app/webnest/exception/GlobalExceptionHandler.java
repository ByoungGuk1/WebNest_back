package com.app.webnest.exception;

import com.app.webnest.domain.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // MyTest 관련 예외 처리
  @ExceptionHandler(MyTestException.class)
  public ResponseEntity<ApiResponseDTO> handleMyTestException(MyTestException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseDTO.of(e.getMessage()));
  }

  // JwtToken 관련 예외 처리
  @ExceptionHandler(JwtTokenException.class)
  public ResponseEntity<ApiResponseDTO> handleJwtTokenException(JwtTokenException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseDTO.of(e.getMessage()));
  }

  // User 관련 예외 처리
  @ExceptionHandler(UserException.class)
  public ResponseEntity<ApiResponseDTO> handleUserException(UserException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseDTO.of(e.getMessage()));
  }

  // 모든 예외 처리
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseDTO<Object>> handleException(Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseDTO.of(e.getMessage()));
  }
}
