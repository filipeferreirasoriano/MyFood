package MyFood.models;

public class Farmacia extends Enterprise {
    Boolean aberto24Horas;
    int numeroFuncionarios;

    public Farmacia() {

    }

    public Farmacia(String typeEnterprise, int dono, String name, String address,Boolean aberto24Horas, int numeroFuncionarios) {
        super(typeEnterprise, dono, name, address);
        this.aberto24Horas = aberto24Horas;
        this.numeroFuncionarios = numeroFuncionarios;
    }
}

