package com.tracking.api_tracking.config;
import com.tracking.api_tracking.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex)
    {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return  ResponseEntity.badRequest().body(
                ApiResponse.builder().status(false).message(message).build()
        );
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntime(RuntimeException ex)
    {
        return  ResponseEntity.badRequest().body(
                ApiResponse.builder().status(false).message(ex.getMessage()).build()
        );
    }
}
