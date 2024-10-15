package MyFood.Exceptions;

public class DataInvalidaException extends RuntimeException {
    public DataInvalidaException() {
        super("Formato de hora invalido");
    }
}
