package MyFood.Exceptions;

public class IdEntregaNaoExisteException extends RuntimeException {
    public IdEntregaNaoExisteException() {
        super("Nao existe entrega com esse id");
    }
}
