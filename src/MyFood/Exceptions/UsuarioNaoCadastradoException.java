package MyFood.Exceptions;

public class UsuarioNaoCadastradoException extends RuntimeException {
    public UsuarioNaoCadastradoException() {
        super("Usuario nao cadastrado.");
    }
}
