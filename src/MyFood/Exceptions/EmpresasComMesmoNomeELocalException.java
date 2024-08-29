package MyFood.Exceptions;

public class EmpresasComMesmoNomeELocalException extends RuntimeException {
    public EmpresasComMesmoNomeELocalException() {
        super("Proibido cadastrar duas empresas com o mesmo nome e local");
    }
}
