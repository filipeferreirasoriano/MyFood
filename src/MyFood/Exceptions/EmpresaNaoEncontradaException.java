package MyFood.Exceptions;

public class EmpresaNaoEncontradaException extends RuntimeException {
    public EmpresaNaoEncontradaException() {
        super("Empresa nao encontrada");
    }
}
