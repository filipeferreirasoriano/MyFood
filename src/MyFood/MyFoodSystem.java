package MyFood;

import MyFood.Product.Product;
import MyFood.User.Manager;
import MyFood.User.User;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyFoodSystem {

    private ArrayList<User> users;
    private HashMap<Integer, ArrayList<Enterprise>> enterprises;
    private HashMap<Integer, ArrayList<Product>> products;

// Gerenciamento do sistema geral

    public MyFoodSystem() {
        users = new ArrayList<>();
        enterprises = new HashMap<>();
        products = new HashMap<>();
        loadData();
    }

    public void zerarSistema() {
        users.clear();
        enterprises.clear();
        products.clear();
        saveData();
    }

    public void saveData() {
        try (FileOutputStream fos = new FileOutputStream("myfoodsystem.xml");
             XMLEncoder encoder = new XMLEncoder(fos)) {
            encoder.writeObject(users);
            encoder.writeObject(enterprises);
            encoder.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try (FileInputStream fis = new FileInputStream("myfoodsystem.xml");
             XMLDecoder decoder = new XMLDecoder(fis)) {
            users = (ArrayList<User>) decoder.readObject();
            enterprises = (HashMap<Integer, ArrayList<Enterprise>>) decoder.readObject();
            products = (HashMap<Integer, ArrayList<Product>>) decoder.readObject();
        } catch (Exception e) {
            users = new ArrayList<>();
            enterprises = new HashMap<>();
            products = new HashMap<>();
        }
    }

// Gerenciamento de usuários

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public String getAtributoUser(int id, String nomeAtributo) {
        User user = getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("Usuario nao cadastrado.");
        }
        return user.getAtribute(nomeAtributo);
    }

    public void createUser(String name, String email, String password, String address) {
        validateUser(name, email, password, address);
        if (emailExist(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }
        User user = new User(name, email, password, address);
        users.add(user);
        saveData();
    }

    public void createUser(String name, String email, String password, String address, String cpf) {
        validateUser(name, email, password, address);
        if (!validateCPF(cpf)) {
            throw new IllegalArgumentException("CPF invalido");
        }
        if (emailExist(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }
        Manager manager = new Manager(name, email, password, address, cpf);
        users.add(manager);
        saveData();
    }

    public int login(String email, String password) {
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Login ou senha invalidos");
        }
        for (User user : users) {
            if (user != null && email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user.getId();
            }
        }
        throw new IllegalArgumentException("Login ou senha invalidos");
    }

    public void encerrarSistema() {
        saveData();
        System.out.println("Sistema encerrado");
    }

    private boolean emailExist(String email) {
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    private void validateUser(String name, String email, String password, String address) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        if (email == null || !validateEmail(email)) {
            throw new IllegalArgumentException("Email invalido");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha invalido");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereco invalido");
        }
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean validateCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }


    public ArrayList<Enterprise> getEnterprises(int dono) {
        User user = getUserById(dono);
        if (user == null || user.getAtribute("cpf") == null) {
            throw new IllegalArgumentException("Usuario nao pode criar uma empresa");
        }

        ArrayList<Enterprise> ans = new ArrayList<>();

        for (ArrayList<Enterprise> enterpriseList : enterprises.values()) {
            for (Enterprise e : enterpriseList) {
                if (e.getDono() == dono) {
                    ans.add(e);
                }
            }
        }

        return ans;
    }

// Gerenciamento de empresas

    public String getEnterprisesHash(int dono) {
        User user = getUserById(dono);
        if (user == null || user.getAtribute("cpf") == null) {
            throw new IllegalArgumentException("Usuario nao pode criar uma empresa");
        }

        String ans = "{[[";
        boolean hasEnterprise = false;

        for (ArrayList<Enterprise> enterpriseList : enterprises.values()) {
            for (Enterprise e : enterpriseList) {
                if (e != null && e.getDono() == dono) {
                    if (hasEnterprise) {
                        ans += "], [";
                    }
                    ans += e.getName() + ", " + e.getAddress();
                    hasEnterprise = true;
                }
            }
        }

        if (hasEnterprise) {
            ans += "]]}";
        } else {
            ans = "{[]}";
        }

        return ans;
    }

    public int getIdEnterprise(int idDono, String name, int indice) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        if (indice < 0) {
            throw new IllegalArgumentException("Indice invalido");
        }

        List<Enterprise> enterprisesList = enterprises.get(idDono);

        int count = 0;
        for (Enterprise enterprise : enterprisesList) {
            if (enterprise.getDono() == idDono && enterprise.getName().equals(name)){
                if(count == indice) {
                    return enterprise.getId();
                }
                count++;
            }
        }

        if(count != 0) {
            throw new IllegalArgumentException("Indice maior que o esperado");
        }

        throw new IllegalArgumentException("Nao existe empresa com esse nome");
    }

    public void doesEnterpriseExist(int dono, String name, String address) {
        for (ArrayList<Enterprise> enterpriseList : enterprises.values()) {
            for (Enterprise e : enterpriseList) {
                if (e.getName().equals(name)) {
                    if (e.getDono() == dono && e.getAddress().equals(address)) {
                        throw new IllegalArgumentException("Proibido cadastrar duas empresas com o mesmo nome e local");
                    }
                    if (e.getDono() != dono) {
                        throw new IllegalArgumentException("Empresa com esse nome ja existe");
                    }
                }
            }
        }
    }

    public int createEnterprise(String typeEnterprise, int dono, String name, String address, String typeKitchen) {
        validateEnterprise(typeEnterprise, dono, name, address, typeKitchen);

        ArrayList<Enterprise> managerEnterprises = getEnterprises(dono);
        Enterprise enterprise = new Enterprise(typeEnterprise, dono, name, address, typeKitchen);
        managerEnterprises.add(enterprise);
        enterprises.put(dono, managerEnterprises);

        return enterprise.getId();
    }

    public void validateEnterprise(String typeEnterprise, int dono, String name, String address, String typeKitchen) {
        User user = getUserById(dono);
        if (user == null || user.getAtribute("cpf") == null) {
            throw new IllegalArgumentException("Usuario nao pode criar uma empresa");
        }
        doesEnterpriseExist(dono, name, address);
    }

    public String getAtributeEnterprise(int id, String attribute) {
        if(attribute == null) {
            throw new IllegalArgumentException("Atributo invalido");
        }
        for (ArrayList<Enterprise> enterpriseList : enterprises.values()) {
            for (Enterprise e : enterpriseList) {
                if (e.getId() == id) {
                    switch (attribute) {
                        case "endereco":
                            return e.getAddress();
                        case "nome":
                            return e.getName();
                        case "dono":
                            User user = getUserById(e.getDono());
                            return user != null ? user.getName() : "Desconhecido";
                        case "tipoCozinha":
                            return e.getTypeKitchen();
                        case "tipoEmpresa":
                            return e.getTypeEnterprise();
                        default:
                            throw new IllegalArgumentException("Atributo invalido");
                    }
                }
            }
        }
        throw new IllegalArgumentException("Empresa nao cadastrada");
    }

// Gerenciamento de Produtos

    public int criarProduto(int enterprise, String name, Double valor, String category) {

        if(category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria invalido");
        }

        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }

        if(valor < 0) {
            throw new IllegalArgumentException("Valor invalido");
        }

        ArrayList<Product> enterpriseProducts = products.get(enterprise);
        if(enterpriseProducts == null) {
            enterpriseProducts = new ArrayList<>();
        } else {
            for (Product product : enterpriseProducts) {
                if (product.getName().equals(name)) {
                    throw new IllegalArgumentException("Ja existe um produto com esse nome para essa empresa");
                }
            }
        }

        Product product = new Product(name, valor, category);
        enterpriseProducts.add(product);
        products.put(enterprise,enterpriseProducts);

        return product.getId();
    }

    public void editProduct(int id, String name, Double value, String category) {

        if(category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria invalido");
        }

        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }

        if(value < 0) {
            throw new IllegalArgumentException("Valor invalido");
        }


        Product current = null;
        for(ArrayList<Product> enterpriseProducts : products.values()) {
            for (Product product : enterpriseProducts) {
                if(product.getId() == id) {
                    current = product;
                    product.setName(name);
                    product.setValue(value);
                    product.setCategory(category);
                    break;
                }
            }
        }
        if(current == null) {
            throw new IllegalArgumentException("Produto nao cadastrado");
        }
    }

    public String getProduto(String  name, int enterprise, String atribute) {

        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        if(atribute == null || atribute.trim().isEmpty()) {
            throw new IllegalArgumentException("Atributo invalido");
        }

        ArrayList<Product> enterpriseProducts = products.get(enterprise);
        Product current = null;
        Enterprise currentEterprise = null;
        if(enterpriseProducts == null) {
            enterpriseProducts = new ArrayList<>();
        } else {
            for (Product product : enterpriseProducts) {
                if (product.getName().equals(name)) {
                   current = product;
                }
            }
        }
        for(ArrayList<Enterprise> enterpriseList : enterprises.values()){
            for (Enterprise e : enterpriseList) {
                if (e.getId() == enterprise) {
                    currentEterprise = e;
                    break;
                }
            }
        }
        if(current == null) {
            throw new IllegalArgumentException("Produto nao encontrado");
        }
        if(currentEterprise == null) {
            throw new IllegalArgumentException("Produto nao encontrado");
        }
        return switch (atribute) {
            case "nome" -> current.getName();
            case "valor" -> current.getValue().toString() + "0";
            case "categoria" -> current.getCategory();
            case "empresa" -> currentEterprise.getName();
            default -> throw new IllegalArgumentException("Atributo nao existe");
        };
    }

    public String getProducts(int enterprise) {

        Enterprise currentEterprise = null;
        for(ArrayList<Enterprise> enterpriseList : enterprises.values()){
            for (Enterprise e : enterpriseList) {
                if (e.getId() == enterprise) {
                    currentEterprise = e;
                    break;
                }
            }
        }

        if(currentEterprise == null) {
            throw new IllegalArgumentException("Empresa nao encontrada");
        }

        ArrayList<Product> productsList = products.get(enterprise);
        String ans = "{[";

        if(productsList == null) {
            ans = "{[]}";
            return ans;
        }

        for(Product product : productsList) {
            ans += product.getName();
            if(product != productsList.get(productsList.size() - 1)) {
                ans += ", ";
            }
        }
        ans += "]}";

        return ans;
    }
}
