package com.banana.Bean;

import com.banana.Model.Usuario;
import com.banana.Service.Impl.UsuarioServiceImpl;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {
    private String nome;
    private String login;
    private String senha;
    private String email;
    @Inject
    transient private UsuarioServiceImpl usuarioServiceImpl;

    public String cadastrarUsuario() {
        usuarioServiceImpl.cadastrarUsuario(nome, login, senha, email);
        return "/login.xhtml?faces-redirect=true";
    }
    public String editarUsuario(int id) {
        usuarioServiceImpl.cadastrarUsuario(nome, login, senha, email);
        return "/login.xhtml?faces-redirect=true";
    }

    public List<Usuario> listarUsuario() {
        return usuarioServiceImpl.listarUsuario();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}