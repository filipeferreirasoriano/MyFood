package MyFood.Exceptions;

public class EnderecoInvalidoException extends RuntimeException{
    public EnderecoInvalidoException(){
        super("Endereco invalido");
    }
}
