package MyFood.Exceptions;

public class PedidoJaLiberadoException extends RuntimeException {
    public PedidoJaLiberadoException() {
        super("Pedido ja liberado");
    }
}
