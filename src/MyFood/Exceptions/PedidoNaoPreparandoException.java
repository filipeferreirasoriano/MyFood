package MyFood.Exceptions;

public class PedidoNaoPreparandoException extends RuntimeException {
    public PedidoNaoPreparandoException() {
        super("Nao e possivel liberar um produto que nao esta sendo preparado");
    }
}
