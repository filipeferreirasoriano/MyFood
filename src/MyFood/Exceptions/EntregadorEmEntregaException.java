package MyFood.Exceptions;

public class EntregadorEmEntregaException extends RuntimeException {
    public EntregadorEmEntregaException() {
        super("Entregador ainda em entrega");
    }
}
