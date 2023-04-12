package pt.credibom.checklist.interfaces.rest;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pt.credibom.checklist.exceptions.ApiException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler({ ConstraintViolationException.class, ValidationException.class })
    public ResponseEntity<String> handleValidationException(ConstraintViolationException ex) {    

        log.warn("Validation failed: {}", ex.getMessage());

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler( ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException ex) {    

        log.warn("ApiException: {}", ex.getMessage());

        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleException(Exception ex) {

        log.error("Request failed", ex);

        return ResponseEntity.internalServerError().build();
    }

 
}
