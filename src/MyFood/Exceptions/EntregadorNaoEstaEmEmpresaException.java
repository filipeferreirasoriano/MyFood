package MyFood.Exceptions;

public class EntregadorNaoEstaEmEmpresaException extends RuntimeException {
    public EntregadorNaoEstaEmEmpresaException() {
        super("Entregador nao esta em nenhuma empresa.");
    }
}
