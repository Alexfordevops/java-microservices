package boxsystem.auth_service.ExceptionHandler.AuthExceptions;

//Exception para usuario não encontrado
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
