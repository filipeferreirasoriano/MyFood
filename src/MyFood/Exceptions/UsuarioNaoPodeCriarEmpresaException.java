package MyFood.Exceptions;

public class UsuarioNaoPodeCriarEmpresaException extends RuntimeException {
    public UsuarioNaoPodeCriarEmpresaException() {
        super("Usuario nao pode criar uma empresa");
    }
}
