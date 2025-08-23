package boxsystem.product_service.ExceptionHandler.productsExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
