package MyFood.Exceptions;

public class ProdutoDeMesmoNomeEEmpresaException extends RuntimeException {
    public ProdutoDeMesmoNomeEEmpresaException() {
        super("Ja existe um produto com esse nome para essa empresa");
    }
}
