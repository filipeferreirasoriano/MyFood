package MyFood;

import MyFood.User.Manager;
import MyFood.User.User;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MyFoodSystem {

    private ArrayList<User> users;

    public MyFoodSystem() {
        users = new ArrayList<>();
        carregarDados();
    }

    public void zerarSistema() {
        salvarDados();
        users.clear();
    }

    public String getAtributoUsuario(int id, String nomeAtributo) {
        User user = null;
        for(User u : users) {
           if(u.getId() == id) {
               user = u;
           }
        }
        if (user == null) {
            throw new IllegalArgumentException("Usuario nao cadastrado.");
        }
        return user.getAtributo(nomeAtributo);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) {
        validarUsuario(nome, email, senha, endereco);
        if (emailJaCadastrado(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }
        User user = new User(nome, email, senha,endereco);
        users.add(user);
        salvarDados();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        validarUsuario(nome, email, senha, endereco);
        if (validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF invalido");
        }
        if (emailJaCadastrado(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }
        Manager manager = new Manager(nome, email, senha, endereco, cpf);
        users.add(manager);
        salvarDados();
    }

    public int login(String email, String senha) {
        for (User user : users) {
            if (user != null) {
                String emailUsuario = user.getEmail();
                String senhaUsuario = user.getPassword();
                if (emailUsuario != null && senhaUsuario != null) {
                    if (emailUsuario.equals(email) && senhaUsuario.equals(senha)) {
                        return user.getId();
                    }
                }
            }
        }
        throw new IllegalArgumentException("Login ou senha invalidos");
    }

    public void encerrarSistema() {
        salvarDados();
        System.out.println("Sistema encerrado");
    }

    private boolean emailJaCadastrado(String email) {
        for (User user : users) {
            if (user != null) {
                String emailUsuario = user.getEmail();
                if (emailUsuario != null && emailUsuario.equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void validarUsuario(String nome, String email, String senha, String endereco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email invalido");
        } else if (!validarEmail(email)) {
            throw new IllegalArgumentException("Formato de email invalido");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha invalido");
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereco invalido");
        }
    }

    private boolean validarEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return !email.isEmpty() && email.matches(emailRegex);
    }

    private boolean validarCPF(String cpf) {
        return cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public void salvarDados() {
        try (FileOutputStream fos = new FileOutputStream("myfoodsystem.xml");
             XMLEncoder encoder = new XMLEncoder(fos)) {
                encoder.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        try (FileInputStream fis = new FileInputStream("myfoodsystem.xml");
             XMLDecoder decoder = new XMLDecoder(fis)) {
            users = (ArrayList<User>) decoder.readObject();
        } catch (IOException e) {
            users = new ArrayList<>();
        }
    }
}
