package ie.atu.lab3cicddp.controller.errorHandling;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExceptionDetails> showExceptionDetails(MethodArgumentNotValidException mae)
    {
        List<ExceptionDetails> errorlist = new ArrayList<>();>
        for(FieldError fieldError : mae.getBindingResult().getFieldErrors())
        {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setFirstName(fieldError.getField());
        exceptionDetails.setFieldValue(fieldError.getDefaultMessage());

        }
        return errorlist;
    }
}
