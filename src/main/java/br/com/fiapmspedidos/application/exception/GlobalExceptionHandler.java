package br.com.fiapmspedidos.application.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError.getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessExceptions(BusinessException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(FeignClientException.class)
    public ResponseEntity<?> handleFeignClientExceptions(FeignClientException ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<?> handleIllegalRequestExceptions(IllegalRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
