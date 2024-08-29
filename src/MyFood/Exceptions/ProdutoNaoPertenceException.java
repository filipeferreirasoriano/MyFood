package MyFood.Exceptions;

public class ProdutoNaoPertenceException extends RuntimeException {
    public ProdutoNaoPertenceException() {
        super("O produto nao pertence a essa empresa");
    }
}

