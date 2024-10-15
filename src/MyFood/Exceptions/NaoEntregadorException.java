package MyFood.Exceptions;

public class NaoEntregadorException extends RuntimeException {
    public NaoEntregadorException() {
        super("Usuario nao e um entregador");
    }
}
