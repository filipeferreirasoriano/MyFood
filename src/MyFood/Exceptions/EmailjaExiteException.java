package MyFood.Exceptions;

public class EmailjaExiteException extends RuntimeException {
    public EmailjaExiteException() {
        super("Conta com esse email ja existe");
    }
}
