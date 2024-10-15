package MyFood.Exceptions;

public class TipodeEmpresaInvalidoException extends RuntimeException {
    public TipodeEmpresaInvalidoException() {
        super("Tipo de empresa invalido");
    }
}
