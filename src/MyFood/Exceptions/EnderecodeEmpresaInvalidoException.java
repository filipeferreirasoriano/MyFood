package MyFood.Exceptions;

public class EnderecodeEmpresaInvalidoException extends RuntimeException {
    public EnderecodeEmpresaInvalidoException() {
        super("Endereco da empresa invalido");
    }
}
