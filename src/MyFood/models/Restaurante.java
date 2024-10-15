package MyFood.models;

import java.util.UUID;

public class Restaurante extends Enterprise {
    public Restaurante() {

    }
    public Restaurante(String name, int dono, String address, String typeEnterprise, String type) {
        super(name, dono, address, typeEnterprise, type);
    }
}
