package MyFood.Exceptions;

public class LoginOuSenhaInvalidosExcepiton extends RuntimeException {
    public LoginOuSenhaInvalidosExcepiton() {
        super("Login ou senha invalidos");
    }
}
