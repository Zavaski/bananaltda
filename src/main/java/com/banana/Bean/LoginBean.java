package com.banana.Bean;

import com.banana.Model.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private String login;
    private String senha;
    private Usuario usuario;

    public String Logar() {
        System.out.println("logando");
        //buscar no banco

        if(this.login.equals("w") && this.senha.equals("1")) {
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