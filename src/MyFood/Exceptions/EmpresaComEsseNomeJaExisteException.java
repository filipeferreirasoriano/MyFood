package MyFood.Exceptions;

public class EmpresaComEsseNomeJaExisteException extends RuntimeException {
    public EmpresaComEsseNomeJaExisteException() {
        super("Empresa com esse nome ja existe");
    }
}
