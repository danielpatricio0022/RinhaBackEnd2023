package org.rinha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceExceptions {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBadRequestException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build(); // 422
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<?> handleBadRequestException(jakarta.validation.ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<?> handleValidationException(UnprocessableEntityException ex) {

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY) // 422
                .body(Map.of(
                        "status", HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        "error", "Unprocessable Entity",
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
    }

    @ExceptionHandler(ExceptionBadRequest.class)
    public ResponseEntity<String> handleBadRequestException(ExceptionBadRequest ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
    }






}
