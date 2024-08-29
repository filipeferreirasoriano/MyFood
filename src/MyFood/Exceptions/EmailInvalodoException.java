package MyFood.Exceptions;

public class EmailInvalodoException extends RuntimeException{
    public EmailInvalodoException(){
        super("Email invalido");
    }
}
