package MyFood.User;

import java.util.Locale;
import java.util.UUID;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String cpf;
    private boolean isManager;

    public User(String name, String email, String password, String address) {
        this.id = UUID.randomUUID().hashCode();
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public User(String name, String email, String password, String address, String cpf) {
        this.id = UUID.randomUUID().hashCode();
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.cpf = cpf;
    }

    public User() {
        this.id = UUID.randomUUID().hashCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEndereco() {
        return this.address;
    }

    public void setEndereco(String address) {
        this.address = address;
    }

    public void setManager() {
        this.isManager = true;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAtribute(String nomeAtributo) {
        switch (nomeAtributo) {
            case "nome": return getName();
            case "email": return getEmail();
            case "endereco": return getEndereco();
            case "senha" : return getPassword();
            case "cpf": return getCpf();
        }
        return null;
    }
}
