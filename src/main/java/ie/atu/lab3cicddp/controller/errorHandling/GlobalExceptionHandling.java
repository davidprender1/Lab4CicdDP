package ie.atu.lab3cicddp.controller.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {

    // 400 – validation errors (return a list of {fieldName, fieldValue})
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDetails>> showExceptionDetails(MethodArgumentNotValidException mae) {
        List<ExceptionDetails> errorList = new ArrayList<>();
        for (FieldError fe : mae.getBindingResult().getFieldErrors()) {
            errorList.add(new ExceptionDetails(fe.getField(), fe.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    // 409 – duplicate
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ExceptionDetails> showDupError(DuplicateException ex) {
        ExceptionDetails details = new ExceptionDetails("passengerID", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(details);
    }

    // 404 – not found
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> notFound(NotFoundException ex) {
        ExceptionDetails details = new ExceptionDetails("resource", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }
}
