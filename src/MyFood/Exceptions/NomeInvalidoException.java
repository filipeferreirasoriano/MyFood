package MyFood.Exceptions;

public class NomeInvalidoException extends RuntimeException{
    public NomeInvalidoException(){
        super("Nome invalido");
    }
}
