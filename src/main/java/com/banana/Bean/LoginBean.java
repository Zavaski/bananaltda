package com.banana.Bean;

import com.banana.Model.Usuario;
import com.banana.Service.Impl.LoginServiceImpl;
import com.banana.Service.Impl.UsuarioServiceImpl;

import javax.ejb.Asynchronous;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private String login;
    private String senha;
    private Usuario usuario;
    private LoginServiceImpl loginService;

    public String Logar() {
//        if(this.login.equals("w") && this.senha.equals("1")) {
        loginService = new LoginServiceImpl();
        if (loginService.verificarLoginPassword(login, senha)) {
            System.out.println("Logar()");
            usuario = new Usuario();
            return "/restrito/inicioSistema.xhtml?faces-redirect=true";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "login ou senha inválidos"));

        return "";
    }
    public String Deslogar(){
        System.out.println("Deslogar()");
        usuario = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}