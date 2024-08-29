package MyFood.Exceptions;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException(){
        super("Senha invalido");
    }
}
