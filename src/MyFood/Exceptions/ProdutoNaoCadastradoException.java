package MyFood.Exceptions;

public class ProdutoNaoCadastradoException extends RuntimeException {
    public ProdutoNaoCadastradoException() {
        super("Produto nao cadastrado");
    }
}
