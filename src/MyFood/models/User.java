package MyFood.models;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String type;
    private ArrayList<Enterprise> enterprises;

    public User(String name, String email, String password, String address) {
        this.id = UUID.randomUUID().hashCode();
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.type = "User";
        this.enterprises = new ArrayList<>();
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Enterprise> getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(ArrayList<Enterprise> enterprises) {
        this.enterprises = enterprises;
    }

    public void addEnterprise(Enterprise enterprise) {
        this.enterprises.add(enterprise);
    }

    public String getAttribute(String attributeName) {
        switch (attributeName) {
            case "nome": return getName();
            case "email": return getEmail();
            case "endereco": return getAddress();
            case "senha" : return getPassword();
        }
        return null;
    }
}
