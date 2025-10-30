package ie.atu.lab3cicddp.controller.errorHandling;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
