package MyFood.Exceptions;

public class IndiceMaiorQueOEsperadoException extends RuntimeException {
    public IndiceMaiorQueOEsperadoException() {
        super("Indice maior que o esperado");
    }
}
