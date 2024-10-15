package MyFood.models;

import java.time.LocalTime;

public class Mercado extends Enterprise {
    private LocalTime abre;
    private LocalTime fecha;

    public Mercado() {

    }

    public Mercado(String typeEnterprise, int dono, String name, String address, String type, LocalTime abre, LocalTime fecha) {
        super(typeEnterprise, dono, name, address, type);
        this.abre = abre;
        this.fecha = fecha;
    }

    public LocalTime getAbre() {
        return abre;
    }
    public void setAbre(LocalTime abre) {
        this.abre = abre;
    }
    public LocalTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalTime fecha) {
        this.fecha = fecha;
    }

}
