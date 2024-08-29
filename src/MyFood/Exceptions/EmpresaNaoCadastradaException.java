package MyFood.Exceptions;

public class EmpresaNaoCadastradaException extends RuntimeException {
    public EmpresaNaoCadastradaException() {
        super("Empresa nao cadastrada");
    }
}
