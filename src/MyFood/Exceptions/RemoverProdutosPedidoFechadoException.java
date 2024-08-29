package MyFood.Exceptions;

public class RemoverProdutosPedidoFechadoException extends RuntimeException {
    public RemoverProdutosPedidoFechadoException() {
        super("Nao e possivel remover produtos de um pedido fechado");
    }
}
