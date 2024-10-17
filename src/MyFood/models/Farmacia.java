package MyFood.models;

import MyFood.Exceptions.AtributoInvalidoException;

public class Farmacia extends Enterprise {
    private Boolean aberto24Horas;
    private int numeroFuncionarios;

    public Farmacia() {
        super();
    }

    public Farmacia(String typeEnterprise, int dono, String name, String address,Boolean aberto24Horas, int numeroFuncionarios) {
        super(typeEnterprise, dono, name, address);
        this.aberto24Horas = aberto24Horas;
        this.numeroFuncionarios = numeroFuncionarios;
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

