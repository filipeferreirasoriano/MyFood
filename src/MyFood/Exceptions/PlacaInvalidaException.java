package MyFood.Exceptions;

public class PlacaInvalidaException extends RuntimeException {
    public PlacaInvalidaException() {
        super("Placa invalido");
    }
}
