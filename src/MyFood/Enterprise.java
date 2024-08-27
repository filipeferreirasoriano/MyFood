package MyFood;

import java.util.UUID;

public class Enterprise {

    private int id;
    private int dono;
    private String name;
    private String address;
    private String typeEnterprise;
    private String typeKitchen;

    public Enterprise() {

    }

    public Enterprise(String typeEnterprise, int dono, String name, String address, String typeKitchen) {
        this.id = UUID.randomUUID().hashCode();
        this.dono = dono;
        this.name = name;
        this.address = address;
        this.typeEnterprise = typeEnterprise;
        this.typeKitchen = typeKitchen;
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

    public String getTypeKitchen() {
        return typeKitchen;
    }

    public void setTypeKitchen(String typeKitchen) {
        this.typeKitchen = typeKitchen;
    }

}
