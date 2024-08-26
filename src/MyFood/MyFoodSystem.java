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

    public String getAtributoUsuario(int id, String nomeAtributo) {
       for(User u : users) {
           if(u.getId() == id) {
               switch(nomeAtributo) {
                   case "nome": return u.getName();
                   case "senha" :return u.getPassword();
                   case "email": return u.getEmail();
                   case "cpf", "endereco": return u.getAttribute(nomeAtributo);
               }
           }
       }
       throw new IllegalArgumentException("Usuario nao cadastrado.");
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) {
        validarUsuario(nome, email, senha, endereco);
        if (emailJaCadastrado(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }
        User user = new User(nome, email, senha);
        user.setAttribute("endereco", endereco);
        users.add(user);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        validarUsuario(nome, email, senha, endereco);
        if (validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF invalido");
        }
        if (emailJaCadastrado(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }
        User user = new User(nome, email, senha);
        user.setAttribute("endereco", endereco);
        user.setAttribute("cpf", cpf);
        users.add(user);
    }


    public int login(String email, String senha) {
        for (User user : users) {
            if (user != null) {
                String emailUsuario = user.getEmail();
                String senhaUsuario = user.getPassword();
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
        System.out.println("Sistema encerrado");
    }

    private boolean emailJaCadastrado(String email) {
        for (User user : users) {
            if (user != null) {
                String emailUsuario = user.getEmail();
                if(emailUsuario != null && emailUsuario.equals(email)) {
                        return true;
                }
            }
        }
        return false;
    }

    private User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private void validarUsuario(String nome, String email, String senha, String endereco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        if (email != null && email.trim().isEmpty()) {
            throw new IllegalArgumentException("Formato de email invalido");
        } else if(email == null || !validarEmail(email)){
            throw new IllegalArgumentException("Email invalido");
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
        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
    }

    private boolean validarCPF(String cpf) {
        return cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}");
    }
}
