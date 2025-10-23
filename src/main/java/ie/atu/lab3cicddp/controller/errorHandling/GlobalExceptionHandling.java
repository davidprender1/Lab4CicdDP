package ie.atu.lab3cicddp.controller.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDetails>> showExceptionDetails(MethodArgumentNotValidException mae)
    {
        List<ExceptionDetails> errorlist = new ArrayList<>();
        for(FieldError fieldError : mae.getBindingResult().getFieldErrors())
        {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setFirstName(fieldError.getField());
        exceptionDetails.setFieldValue(fieldError.getDefaultMessage());

        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorlist);
    }
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ExceptionDetails> showDupError(DuplicateException de)
    {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setFirstName("Passenger ID");
        exceptionDetails.setFieldValue(de.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDetails);
    }
}
