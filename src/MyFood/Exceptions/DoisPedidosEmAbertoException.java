package MyFood.Exceptions;

public class DoisPedidosEmAbertoException extends RuntimeException {
    public DoisPedidosEmAbertoException() {
        super("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
    }
}
