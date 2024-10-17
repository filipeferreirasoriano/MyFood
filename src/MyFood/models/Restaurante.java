package MyFood.models;
import MyFood.Exceptions.AtributoInvalidoException;

public class Restaurante extends Enterprise {
    public Restaurante() {
        super();
    }
    public Restaurante(String name, int dono, String address, String typeEnterprise, String type) {
        super(name, dono, address, typeEnterprise, type);
    }

    @Override
    public String getAttribute(String attribute, User manager) {
        return switch (attribute) {
            case "endereco" -> getAddress();
            case "nome" -> getName();
            case "dono" -> manager != null ? manager.getName() : "Desconhecido";
            case "tipoCozinha" -> getType();
            case "tipoEmpresa" -> getTypeEnterprise();
            default -> throw new AtributoInvalidoException();
        };
    }
}
