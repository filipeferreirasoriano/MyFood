package MyFood.Exceptions;

public class NaoExisteIdEntregaException extends RuntimeException {
    public NaoExisteIdEntregaException() {
        super("Nao existe nada para ser entregue com esse id");
    }
}
