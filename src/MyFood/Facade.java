package MyFood;
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
}