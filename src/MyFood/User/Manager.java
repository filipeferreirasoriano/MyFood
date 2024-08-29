package MyFood.User;

public class Manager extends User {
    private String cpf;

    public Manager(String nome, String email, String password, String endereco, String cpf) {
        super(nome, email, password, endereco);
        this.cpf = cpf;
    }
    public Manager() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getAtribute(String nomeAtributo) {
        if ("cpf".equals(nomeAtributo)) {
            return this.cpf;
        } else {
            return super.getAtribute(nomeAtributo);
        }
    }

}
