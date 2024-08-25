package MyFood;
import java.util.UUID;

public class Facade {
    private final MyFoodSystem system;

    public Facade() {
        system = new MyFoodSystem();
    }

    public void zerarSistema() {
        system.zerarSistema();
    }

    public String getAtributoUsuario(String id, String nomeAtributo) {
        return system.getAtributoUsuario(id, nomeAtributo);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) {
        system.criarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        system.criarUsuario(nome, email, senha, endereco, cpf);
    }

    public UUID login(String email, String senha) {
        return system.login(email, senha);
    }

    public void encerrarSistema() {
        system.encerrarSistema();
    }
}
