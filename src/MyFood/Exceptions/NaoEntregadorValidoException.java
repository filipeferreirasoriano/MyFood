package MyFood.Exceptions;

public class NaoEntregadorValidoException extends RuntimeException {
    public NaoEntregadorValidoException() {
        super("Nao e um entregador valido");
    }
}
