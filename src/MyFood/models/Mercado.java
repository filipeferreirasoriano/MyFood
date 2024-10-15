package MyFood.models;

import MyFood.Exceptions.AtributoInvalidoException;

public class Mercado extends Enterprise {
    private String abre;
    private String fecha;

    public Mercado() {

    }

    public Mercado(String typeEnterprise, int dono, String name, String address, String type, String abre, String fecha) {
        super(typeEnterprise, dono, name, address, type);
        this.abre = abre;
        this.fecha = fecha;
    }

    public String getAbre() {
        return abre;
    }
    public void setAbre(String abre) {
        this.abre = abre;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String getAttribute(String attribute, User manager) {
        return switch (attribute) {
            case "endereco" -> getAddress();
            case "nome" -> getName();
            case "dono" -> manager != null ? manager.getName() : "Desconhecido";
            case "tipoMercado" -> getType();
            case "tipoEmpresa" -> getTypeEnterprise();
            case "abre" -> getAbre();
            case "fecha" -> getFecha();
            default -> throw new AtributoInvalidoException();
        };
    }
}
