package MyFood.User;

import java.util.Map;
import java.util.UUID;

public class User {
    private final int id;
    private String name;
    private String email;
    private String password;
    private String endereco;

    public User(String name, String email, String password, String endereco) {
        this.id = UUID.randomUUID().hashCode();
        this.name = name;
        this.email = email;
        this.password = password;
        this.endereco = endereco;
    }
    public User() {
        this.id = UUID.randomUUID().hashCode();
    }
    public int getId() {
        return id;
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
        return this.endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getAtributo(String nomeAtributo) {
        switch (nomeAtributo) {
            case "nome": return getName();
            case "email": return getEmail();
            case "endereco": return getEndereco();
            case "senha" : return getPassword();
        }
        return null;
    }
}
