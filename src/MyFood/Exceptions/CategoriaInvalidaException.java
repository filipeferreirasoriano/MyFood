package MyFood.Exceptions;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException() {
        super("Categoria invalido");
    }
}
