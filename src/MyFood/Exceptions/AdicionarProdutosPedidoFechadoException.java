package MyFood.Exceptions;

public class AdicionarProdutosPedidoFechadoException extends RuntimeException {
    public AdicionarProdutosPedidoFechadoException() {
        super("Nao e possivel adicionar produtos a um pedido fechado");
    }
}
