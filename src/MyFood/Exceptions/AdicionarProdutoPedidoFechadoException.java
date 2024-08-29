package MyFood.Exceptions;

public class AdicionarProdutoPedidoFechadoException extends RuntimeException {
    public AdicionarProdutoPedidoFechadoException() {
        super("Nao e possivel adicionar produtos a um pedido fechado");
    }
}
