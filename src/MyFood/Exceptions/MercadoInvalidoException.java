package MyFood.Exceptions;

public class MercadoInvalidoException extends RuntimeException {
    public MercadoInvalidoException() {
        super("Nao e um mercado valido");
    }
}
