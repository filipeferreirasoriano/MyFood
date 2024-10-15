package MyFood.models;

import MyFood.Exceptions.AtributoInvalidoException;

import java.util.UUID;

public class Enterprise {

    private int id;
    private int dono;
    private String name;
    private String address;
    private String typeEnterprise;
    private String type;

    public Enterprise() {

    }

    public Enterprise(String typeEnterprise, int dono, String name, String address, String type) {
        this.id = UUID.randomUUID().hashCode();
        this.dono = dono;
        this.name = name;
        this.address = address;
        this.typeEnterprise = typeEnterprise;
        this.type = type;
    }

    public Enterprise(String typeEnterprise, int dono, String name, String address) {
        this.id = UUID.randomUUID().hashCode();
        this.dono = dono;
        this.name = name;
        this.address = address;
        this.typeEnterprise = typeEnterprise;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDono() {
        return dono;
    }
    public void setDono(int dono) {
        this.dono = dono;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeEnterprise() {
        return typeEnterprise;
    }

    public void setTypeEnterprise(String typeEnterprise) {
        this.typeEnterprise = typeEnterprise;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribute(String attribute, User manager) {
        return switch (attribute) {
            case "endereco" -> getAddress();
            case "nome" -> getName();
            case "dono" -> manager != null ? manager.getName() : "Desconhecido";
            case "tipoEmpresa" -> getTypeEnterprise();
            default -> throw new AtributoInvalidoException();
        };
    }
}
