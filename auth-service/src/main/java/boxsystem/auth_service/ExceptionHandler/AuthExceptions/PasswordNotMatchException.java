package boxsystem.auth_service.ExceptionHandler.AuthExceptions;

//Exception para senha não confere
public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
