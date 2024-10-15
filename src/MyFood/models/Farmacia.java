package MyFood.models;

import MyFood.Exceptions.AtributoInvalidoException;

import java.util.ArrayList;

public class Farmacia extends Enterprise {
    private Boolean aberto24Horas;
    private int numeroFuncionarios;
    private ArrayList<DeliveryMan> deliveryMans;

    public Farmacia() {

    }

    public Farmacia(String typeEnterprise, int dono, String name, String address,Boolean aberto24Horas, int numeroFuncionarios) {
        super(typeEnterprise, dono, name, address);
        this.aberto24Horas = aberto24Horas;
        this.numeroFuncionarios = numeroFuncionarios;
    }

    public ArrayList<DeliveryMan> getDeliveryMans() {
        return deliveryMans;
    }

    public void setDeliveryMans(ArrayList<DeliveryMan> deliveryMans) {
        this.deliveryMans = deliveryMans;
    }

    public Boolean getAberto24Horas() {
        return aberto24Horas;
    }

    public void setAberto24Horas(Boolean aberto24Horas) {
        this.aberto24Horas = aberto24Horas;
    }

    public int getNumeroFuncionarios() {
        return numeroFuncionarios;
    }

    public void setNumeroFuncionarios(int numeroFuncionarios) {
        this.numeroFuncionarios = numeroFuncionarios;
    }

    public void addDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMans.add(deliveryMan);
    }

    @Override
    public String getAttribute(String attribute, User manager) {
        return switch (attribute) {
            case "endereco" -> getAddress();
            case "nome" -> getName();
            case "dono" -> manager != null ? manager.getName() : "Desconhecido";
            case "aberto24Horas" -> getAberto24Horas().toString();
            case "numeroFuncionarios" -> String.valueOf(numeroFuncionarios);
            case "tipoEmpresa" -> getTypeEnterprise();
            default -> throw new AtributoInvalidoException();
        };
    }
}

