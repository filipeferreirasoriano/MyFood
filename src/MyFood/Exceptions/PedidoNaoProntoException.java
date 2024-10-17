package MyFood.Exceptions;

public class PedidoNaoProntoException extends RuntimeException{
    public PedidoNaoProntoException() {
        super("Pedido nao esta pronto para entrega");
    }
}
