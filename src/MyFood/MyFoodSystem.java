package MyFood;

import MyFood.User.User;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

public class MyFoodSystem {
    private final ArrayList<User> users;

    public MyFoodSystem() {
        users = new ArrayList<>();
    }

    public void zerarSistema() {
        users.clear();
    }

    public String getAtributoUsuario(String idStr, String nomeAtributo) {
        try {
            UUID id = UUID.fromString(idStr);
            User user = getUserById(id);
            if (user == null) {
                throw new IllegalArgumentException("Usuario nao cadastrado.");
            }
            if (user.hasAttribute(nomeAtributo)) {
                return user.getAttribute(nomeAtributo);
            } else {
                throw new IllegalArgumentException("Atributo nao encontrado.");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Usuario nao cadastrado.");
        }
    }


    public void criarUsuario(String nome, String email, String senha, String endereco) {
        validarUsuario(nome, email, senha, endereco, null);
        if (emailJaCadastrado(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe.");
        }
        User user = new User(nome, email, senha);
        user.setAttribute("endereco", endereco);
        users.add(user);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        validarUsuario(nome, email, senha, endereco, cpf);
        if (emailJaCadastrado(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe.");
        }
        if (validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF invalido");
        }
        User user = new User(nome, email, senha);
        user.setAttribute("endereco", endereco);
        user.setAttribute("cpf", cpf);
        users.add(user);
    }


    public UUID login(String email, String senha) {
        for (User user : users) {
            if (user != null) {
                String emailUsuario = user.getAttribute("email");
                String senhaUsuario = user.getAttribute("senha");
                if(emailUsuario != null  && senhaUsuario != null) {
                    if(emailUsuario.equals(email) && senhaUsuario.equals(senha)) {
                        return user.getId();
                    }
                }
            }
        }
        throw new IllegalArgumentException("Login ou senha invalidos");
    }

    public void encerrarSistema() {
        System.out.println("Sistema encerrado.");
    }

    private boolean emailJaCadastrado(String email) {
        for (User user : users) {
            if (user != null) {
                String emailUsuario = user.getAttribute("email");
                if(emailUsuario != null && emailUsuario.equals(email)) {
                        return true;
                }
            }
        }
        return false;
    }

    private User getUserById(UUID id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    private void validarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        if (email == null || email.trim().isEmpty() || !validarEmail(email)) {
            throw new IllegalArgumentException("Email invalido");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha invalida");
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereco invalido");
        }
        if (cpf != null && validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF invalido");
        }
    }

    private boolean validarEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
    }

    private boolean validarCPF(String cpf) {
        return cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}");
    }
}
