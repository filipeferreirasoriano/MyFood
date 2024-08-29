package MyFood.Exceptions;

public class AtributoNaoExisteException extends RuntimeException {
    public AtributoNaoExisteException(){
        super("Atributo nao existe");
    }
}
