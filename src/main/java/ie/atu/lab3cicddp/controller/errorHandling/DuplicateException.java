package ie.atu.lab3cicddp.controller.errorHandling;

public class DuplicateException extends RuntimeException {
    private String message;
    private String field;
    public DuplicateException(String field,String message)
    {
     this.message = message;
    }
    public DuplicateException(String message)
    {
        super(message);
    }
}
