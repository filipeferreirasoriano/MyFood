package MyFood;

import MyFood.Exceptions.*;
import MyFood.models.*;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class MyFoodSystem {

    private ArrayList<User> users;
    private ArrayList<ShoppingCart> shoppingCarts;
    private HashMap<Integer, ArrayList<Product>> products;
    private HashMap<Integer, ArrayList<Enterprise>> enterprises;
    private static final DateTimeFormatter dateFormatHour = DateTimeFormatter.ofPattern("HH:mm");

// Gerenciamento do sistema geral

    public MyFoodSystem() {
        users = new ArrayList<>();
        enterprises = new HashMap<>();
        products = new HashMap<>();
        shoppingCarts = new ArrayList<>();
        loadData();
    }

    public void zerarSistema() {
        users.clear();
        enterprises.clear();
        products.clear();
        shoppingCarts.clear();
        saveData();
    }

    public void saveData() {
        try (FileOutputStream fos = new FileOutputStream("myfoodsystem.xml");
            XMLEncoder encoder = new XMLEncoder(fos)) {
            encoder.writeObject(users);
            encoder.writeObject(enterprises);
            encoder.writeObject(products);
            encoder.writeObject(shoppingCarts);
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
            shoppingCarts = (ArrayList<ShoppingCart>) decoder.readObject();
        } catch (Exception e) {
            users = new ArrayList<>();
            enterprises = new HashMap<>();
            products = new HashMap<>();
            shoppingCarts = new ArrayList<>();
        }
    }

// Gerenciamento de usuários

    public User getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public String getAtributoUser(int id, String nomeAtributo) {
        User user = getUserById(id);
        if (user == null) {
            throw new UsuarioNaoCadastradoException();
        }
        return user.getAtribute(nomeAtributo);
    }

    public void createUser(String name, String email, String password, String address) {
        validateUser(name, email, password, address);
        if (emailExist(email)) {
            throw new EmailjaExiteException();
        }
        User user = new User(name, email, password, address);
        users.add(user);
        saveData();
    }

    public void createUser(String name, String email, String password, String address, String cpf) {
        validateUser(name, email, password, address);
        if (!validateCPF(cpf)) {
            throw new CpfInvalidoException();
        }
        if (emailExist(email)) {
            throw new EmailjaExiteException();
        }
        Manager manager = new Manager(name, email, password, address, cpf);
        users.add(manager);
        saveData();
    }

    public int login(String email, String password) {
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new LoginOuSenhaInvalidosExcepiton();
        }
        for (User user : users) {
            if (user != null && email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user.getId();
            }
        }
        throw new LoginOuSenhaInvalidosExcepiton();
    }

    public void encerrarSistema() {
        saveData();
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
            throw new NomeInvalidoException();
        }
        if (email == null || !validateEmail(email)) {
            throw new EmailInvalodoException();
        }
        if (password == null || password.trim().isEmpty()) {
            throw new SenhaInvalidaException();
        }
        if (address == null || address.trim().isEmpty()) {
            throw new EnderecoInvalidoException();
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
            throw new UsuarioNaoPodeCriarEmpresaException();
        }

        ArrayList<Enterprise> ans = enterprises.get(dono);

        if (ans == null) {
            return new ArrayList<>();
        }
        return ans;
    }

// Gerenciamento de empresas

    public String getEnterprisesHash(int dono) {
        User user = getUserById(dono);
        if (user == null || user.getAtribute("cpf") == null) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }

        StringBuilder ans = new StringBuilder("{[[");
        boolean hasEnterprise = false;

        for (ArrayList<Enterprise> enterpriseList : enterprises.values()) {
            for (Enterprise e : enterpriseList) {
                if (e != null && e.getDono() == dono) {
                    if (hasEnterprise) {
                        ans.append("], [");
                    }
                    ans.append(e.getName()).append(", ").append(e.getAddress());
                    hasEnterprise = true;
                }
            }
        }

        if (hasEnterprise) {
            ans.append("]]}");
        } else {
            ans = new StringBuilder("{[]}");
        }

        return ans.toString();
    }

    public int getIdEnterprise(int idDono, String name, int indice) {
        if (name == null || name.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (indice < 0) {
            throw new IndiceInvalidoException();
        }

        List<Enterprise> enterprisesList = enterprises.get(idDono);
        if(enterprisesList == null) {
            return 0;
        }
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
            throw new IndiceMaiorQueOEsperadoException();
        }

        throw new NaoExisteEmpresaComEsseNomeException();
    }

    public void doesEnterpriseExist(int dono, String name, String address) {
        for (ArrayList<Enterprise> enterpriseList : enterprises.values()) {
            for (Enterprise e : enterpriseList) {
                if (e.getName().equals(name)) {
                    if (e.getDono() == dono && e.getAddress().equals(address)) {
                        throw new EmpresasComMesmoNomeELocalException();
                    }
                    if (e.getDono() != dono) {
                        throw new EmpresaComEsseNomeJaExisteException();
                    }
                }
            }
        }
    }

//    public int createEnterprise(String typeEnterprise, int dono, String name,String address, Boolean aberto24Horas, int numeroFuncionarios) {
//
//          validateEnterprise(typeEnterprise, dono, name, address);
//        ArrayList<Enterprise> managerEnterprises = getEnterprises(dono);
//        Enterprise enterprise = new Farmacia(typeEnterprise, dono, name, address,aberto24Horas,numeroFuncionarios);
//        managerEnterprises.add(enterprise);
//        enterprises.put(dono, managerEnterprises);
//
//        return enterprise.getId();
//    }

    public int createEnterprise(String typeEnterprise, int dono, String name, String address, String abre, String fecha, String type) {

        LocalTime open = validDate(abre);
        LocalTime close = validDate(fecha);

        validateEnterprise(typeEnterprise,dono,name,address,open,close,type);
        ArrayList<Enterprise> managerEnterprises = getEnterprises(dono);
        Enterprise enterprise = new Mercado(typeEnterprise, dono, name, address,type, open, close);
        managerEnterprises.add(enterprise);
        enterprises.put(dono, managerEnterprises);
        return enterprise.getId();

    }

    public int createEnterprise(String typeEnterprise, int dono, String name, String address, String typeKitchen) {

        validateEnterprise(typeEnterprise, dono, name, address, typeKitchen);
        ArrayList<Enterprise> managerEnterprises = getEnterprises(dono);
        Enterprise enterprise = new Restaurante(typeEnterprise, dono, name, address, typeKitchen);
        managerEnterprises.add(enterprise);
        enterprises.put(dono, managerEnterprises);

        return enterprise.getId();
    }

    public void validateEnterprise(String typeEnterprise, int dono, String name, String address, String typeKitchen) {
        User user = getUserById(dono);
        if (user == null || user.getAtribute("cpf") == null) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }
        if(typeEnterprise == null || typeEnterprise.trim().isEmpty()) {
            throw new TipodeEmpresaInvalidoException();
        }
        doesEnterpriseExist(dono, name, address);
    }

    public LocalTime validDate(String hour) {

        LocalTime hourTime;
        String regex = "^\\d{2}:\\d{2}$";
        if(hour == null) throw new HorarioInvalidoException();
        if(!hour.matches(regex)) throw new DataInvalidaException();
        try {
            hourTime = LocalTime.parse(hour, dateFormatHour);
        } catch (DateTimeParseException e) {
            throw new HorarioInvalidoException();
        }
        return hourTime;
    }

    public void validateEnterprise(String typeEnterprise, int dono, String name, String address, LocalTime open, LocalTime close, String type) {
        User user = getUserById(dono);

        if (user == null || user.getAtribute("cpf") == null) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }

        if(type == null || type.trim().isEmpty()) {
            throw new TipoMercadoInvalidoExeption();
        }

        if(name == null || name.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        if(address == null || address.trim().isEmpty()) {
            throw new EnderecodeEmpresaInvalidoException();
        }

        if(typeEnterprise == null || typeEnterprise.trim().isEmpty()) {
            throw new TipodeEmpresaInvalidoException();
        }

        if(open.isAfter(close)) {
            throw new HorarioInvalidoException();
        }

        doesEnterpriseExist(dono, name, address);

    }

    public String getAtributeEnterprise(int id, String attribute) {
        if(attribute == null) {
            throw new AtributoInvalidoException();
        }
        for (ArrayList<Enterprise> enterpriseList : enterprises.values()) {
            for (Enterprise e : enterpriseList) {
                if (e.getId() == id) {
                    return switch (attribute) {
                        case "endereco" -> e.getAddress();
                        case "nome" -> e.getName();
                        case "dono" -> {
                            User user = getUserById(e.getDono());
                            yield user != null ? user.getName() : "Desconhecido";
                        }
                        case "tipoCozinha" -> e.getType();
                        case "tipoEmpresa" -> e.getTypeEnterprise();
                        default -> throw new AtributoInvalidoException();
                    };
                }
            }
        }
        throw new EmpresaNaoCadastradaException();
    }

// Gerenciamento de Produtos

    public int criarProduto(int enterprise, String name, Double valor, String category) {

        if(category == null || category.trim().isEmpty()) {
            throw new CategoriaInvalidaException();
        }

        if(name == null || name.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        if(valor < 0) {
            throw new ValorInvalidoException();
        }

        ArrayList<Product> enterpriseProducts = products.get(enterprise);
        if(enterpriseProducts == null) {
            enterpriseProducts = new ArrayList<>();
        } else {
            for (Product product : enterpriseProducts) {
                if (product.getName().equals(name)) {
                    throw new ProdutoDeMesmoNomeEEmpresaException();
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
            throw new CategoriaInvalidaException();
        }

        if(name == null || name.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        if(value < 0) {
            throw new ValorInvalidoException();
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
            throw new ProdutoNaoCadastradoException();
        }
    }

    public String getProduto(String  name, int enterprise, String atribute) {

        if(name == null || name.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }
        if(atribute == null || atribute.trim().isEmpty()) {
            throw new AtributoInvalidoException();
        }

        ArrayList<Product> enterpriseProducts = products.get(enterprise);
        Product current = null;
        Enterprise currentEnterprise = null;

        if(enterpriseProducts != null) {
            for (Product product : enterpriseProducts) {
                if (product.getName().equals(name)) {
                   current = product;
                }
            }
        }
        for(ArrayList<Enterprise> enterpriseList : enterprises.values()){
            for (Enterprise e : enterpriseList) {
                if (e.getId() == enterprise) {
                    currentEnterprise = e;
                    break;
                }
            }
        }
        if(current == null) {
            throw new ProdutoNaoEncontradoException();
        }
        if(currentEnterprise == null) {
            throw new ProdutoNaoEncontradoException();
        }
        return switch (atribute) {
            case "nome" -> current.getName();
            case "valor" -> current.getValue().toString() + "0";
            case "categoria" -> current.getCategory();
            case "empresa" -> currentEnterprise.getName();
            default -> throw new AtributoNaoExisteException();
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
            throw new EmpresaNaoEncontradaException();
        }

        ArrayList<Product> productsList = products.get(enterprise);
        StringBuilder ans = new StringBuilder("{[");

        if(productsList == null) {
            ans = new StringBuilder("{[]}");
            return ans.toString();
        }

        for(Product product : productsList) {
            ans.append(product.getName());
            if(product != productsList.get(productsList.size() - 1)) {
                ans.append(", ");
            }
        }
        ans.append("]}");

        return ans.toString();
    }

    public int createOrder(int clientId, int enterpriseId) {
        if(enterprises.get(clientId) != null) {
            throw new DonoNaoPodeFazerPedidoException();
        }
        if(shoppingCarts.stream().anyMatch(shoppingCart -> shoppingCart.getClientId() == clientId && shoppingCart.getEnterpriseId() == enterpriseId && shoppingCart.isOpenOrder())) {
            throw new DoisPedidosEmAbertoException();
        }

        ShoppingCart shoppingCart = new ShoppingCart(clientId, enterpriseId);
        shoppingCarts.add(shoppingCart);
        return shoppingCart.getOrderId();
    }

    public void addProductToShoppingCart(int orderId, int productId) {
        if(shoppingCarts.isEmpty()) {
            throw new NaoExistePedidoEmAbertoException();
        }

        ShoppingCart shoppingCart = shoppingCarts.stream()
                                    .filter(cart -> cart.getOrderId() == orderId)
                                    .findFirst()
                                    .orElseThrow(NaoExistePedidoEmAbertoException::new);

        if(!shoppingCart.isOpenOrder()) {
            throw new AdicionarProdutosPedidoFechadoException();
        }

        Product product = products.get(shoppingCart.getEnterpriseId()).stream().filter(prod -> prod.getId() == productId).findFirst().orElse(null);
        if(product == null) {
            throw new ProdutoNaoPertenceException();
        }
        shoppingCart.addProduct(product);
    }

    public String getOrderAttribute(int orderId, String attribute) {
        if(attribute == null || attribute.trim().isEmpty()) {
            throw new AtributoInvalidoException();
        }
        ShoppingCart shoppingCart = shoppingCarts.stream().filter(cart -> cart.getOrderId() == orderId)
                                    .findFirst().orElseThrow(NaoExistePedidoEmAbertoException::new);

        if(attribute.equals("cliente")) {
            User user = users.stream().filter(u -> u.getId() == shoppingCart.getClientId())
                                        .findFirst()
                                        .orElseThrow(AtributoNaoExisteException::new);

            return user.getName();
        }
        else if(attribute.equals("empresa")) {
            for(ArrayList<Enterprise> enterpriseList : enterprises.values()){
                Enterprise enterprise = enterpriseList.stream().filter(e -> e.getId() == shoppingCart.getEnterpriseId()).findFirst().orElse(null);
                if(enterprise != null) {
                    return enterprise.getName();
                }
            }
        }
        else if(attribute.equals("estado")) {
            if(shoppingCart.isOpenOrder()) {
                return "aberto";
            }
            else {
                return "preparando";
            }
        }
        else if(attribute.equals("produtos")) {
            ArrayList<Product> productList = shoppingCart.getProducts();

            StringBuilder ans = new StringBuilder("{[");
            boolean first = true;
            for(Product product : productList) {
                if(!first) {
                    ans.append(", ");
                }
                first = false;

                ans.append(product.getName());
            }
            ans.append("]}");
            return ans.toString();
        }
        else if(attribute.equals("valor")) {
            return String.format("%.2f", shoppingCart.getTotalPrice());
        }
        else {
            throw new AtributoNaoExisteException();
        }
        return null;
    }

    public void closeOrder(int orderId) {
        ShoppingCart shoppingCart = shoppingCarts.stream().filter(cart -> cart.getOrderId() == orderId).findFirst().orElse(null);
        if(shoppingCart == null) {
            throw new PedidoNaoEncontradoException();
        }
        shoppingCart.setOpenOrder(false);
    }

    public void removeProductFromShoppingCart(int orderId, String product) {
        if(product == null || product.trim().isEmpty()) {
            throw new ProdutoInvalidoException();
        }

        ShoppingCart shoppingCart = shoppingCarts.stream().filter(cart -> cart.getOrderId() == orderId)
                                    .findFirst().orElseThrow(NaoExistePedidoEmAbertoException::new);

        if(!shoppingCart.isOpenOrder()) {
            throw new RemoverProdutosPedidoFechadoException();
        }
        ArrayList<Product> productList = shoppingCart.getProducts();

        Product productToRemove = productList.stream().filter(prod -> prod.getName().equals(product))
                                    .findFirst().orElseThrow(ProdutoNaoEncontradoException::new);

        shoppingCart.removeProduct(productToRemove);
    }

    public int getOrderId(int clientId, int enterpriseId, int index) {
        List<ShoppingCart> shoppingCart = shoppingCarts.stream().filter(cart -> cart.getClientId() == clientId && cart.getEnterpriseId() == enterpriseId)
                                            .toList();
        return shoppingCart.get(index).getOrderId();
    }
}
