package MyFood.Exceptions;

public class NaoExistePedidoException extends RuntimeException {
    public NaoExistePedidoException() {
        super("Nao existe pedido para entrega");
    }
}
