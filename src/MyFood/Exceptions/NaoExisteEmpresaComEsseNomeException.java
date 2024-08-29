package MyFood.Exceptions;

public class NaoExisteEmpresaComEsseNomeException extends RuntimeException {
    public NaoExisteEmpresaComEsseNomeException() {
        super("Nao existe empresa com esse nome");
    }
}
