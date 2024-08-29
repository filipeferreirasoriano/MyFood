package MyFood.Exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(){
        super("Produto nao encontrado");
    }
}
