package MyFood;

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

    public void criarUsuario(String nome, String email, String senha, String endereco, String veiculo, String placa) {
        system.createUser(nome, email, senha, endereco, veiculo, placa);
    }

    public void cadastrarEntregador(int empresa, int entregador) {
        system.insertDeliveryMan(empresa, entregador);
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
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String abre, String fecha, String tipoMercado) {
        return system.createEnterprise(tipoEmpresa, dono, nome, endereco, abre, fecha, tipoMercado);
    }

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, Boolean aberto24Horas, int numeroFuncionarios) {
        return system.createEnterprise(tipoEmpresa, dono, nome, endereco, aberto24Horas, numeroFuncionarios);
    }

    public int getIdEmpresa (int idDono, String nome, int indice) {
        return system.getIdEnterprise(idDono, nome, indice);
    }

    public String getAtributoEmpresa(int id, String nomeAtributo) {
        return system.getAtributeEnterprise(id, nomeAtributo);
    }

    public void alterarFuncionamento(int id, String abre, String fecha){
        system.changeOperation(id, abre, fecha);
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

    public int getNumeroPedido(int clientId, int enterpriseId, int index) {
        return system.getOrderId(clientId, enterpriseId, index);
    }

    public String getEntregadores(int empresa) {
        return system.getDeliveryMans(empresa);
    }

    public String getEmpresas(int entregador) {
        return system.getEnterprisesDeliveryManString(entregador);
    }
}