package MyFood.Exceptions;

public class DonoNaoPodeFazerPedidoException extends RuntimeException {
    public DonoNaoPodeFazerPedidoException() {
        super("Dono de empresa nao pode fazer um pedido");
    }
}
