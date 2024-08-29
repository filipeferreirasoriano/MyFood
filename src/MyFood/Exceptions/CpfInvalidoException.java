package MyFood.Exceptions;

public class CpfInvalidoException extends RuntimeException{
    public CpfInvalidoException(){
        super("CPF invalido");
    }
}
