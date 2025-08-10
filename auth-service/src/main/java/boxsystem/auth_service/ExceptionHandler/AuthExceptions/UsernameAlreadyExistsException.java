package boxsystem.auth_service.ExceptionHandler.AuthExceptions;

//Exception customizada para user ja cadastrado
public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
