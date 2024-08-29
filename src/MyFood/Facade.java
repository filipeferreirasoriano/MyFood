package MyFood;
import MyFood.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Facade {
    private final MyFoodSystem system;

    public Facade() {
        system = new MyFoodSystem();
    }

    public void zerarSistema() {
        system.zerarSistema();
    }

    public String getAtributoUsuario(int id, String nomeAtributo) {
        return system.getAtributoUser(id, nomeAtributo);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) {
        system.createUser(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        system.createUser(nome, email, senha, endereco, cpf);
    }

    public int login(String email, String senha) {
        return system.login(email, senha);
    }

    public void encerrarSistema() {
        system.encerrarSistema();
    }

    public String getEmpresasDoUsuario(int id) {
        return system.getEnterprisesHash(id);
    }

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) {
        return system.createEnterprise(tipoEmpresa, dono, nome, endereco, tipoCozinha);
    }

    public int getIdEmpresa (int idDono, String nome, int indice) {
        return system.getIdEnterprise(idDono, nome, indice);
    }

    public String getAtributoEmpresa(int id, String nomeAtributo) {
        return system.getAtributeEnterprise(id, nomeAtributo);
    }

    public int criarProduto(int enterprise, String name, Double valor, String category) {
        return system.criarProduto(enterprise, name, valor, category);
    }

    public void editarProduto(int id, String nome, Double valor, String categoria) {
        system.editProduct(id, nome, valor, categoria);
    }

    public String getProduto(String  name, int enterprise, String atribute) {
        return system.getProduto(name, enterprise, atribute);
    }

    public String listarProdutos(int enterprise) {
        return system.getProducts(enterprise);
    }

    public int criarPedido(int clientId, int enterpriseId) {
        return system.createOrder(clientId, enterpriseId);
    }

    public void adicionarProduto(int orderId, int productId) {
        system.addProductToShoppingCart(orderId, productId);
    }

    public String getPedidos(int orderId, String attribute) {
        return system.getOrderAttribute(orderId, attribute);
    }

    public void fecharPedido(int orderId) {
        system.closeOrder(orderId);
    }

    public void removerProduto(int orderId, String product) {
        system.removeProductFromShoppingCart(orderId, product);
    }
}