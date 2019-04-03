package com.banana.Bean;

import com.banana.Model.Local;
import com.banana.Service.Impl.LocalServiceImpl;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("localBean")
@ViewScoped
public class LocalBean implements Serializable {
    private String nome;
    private String CNPJ;
    private String endereco;
    private String IDString = "";
    @Inject
    transient private LocalServiceImpl localService;

    public String salvarLocal() {
        localService.salvarLocal(nome, CNPJ, endereco);
        return "/restrito/local/locaisList.xhtml?faces-redirect=true";
    }
     public String editarLocal(int ID){

        localService.editarTela(ID);
        return "/restrito/local/editLocal.xhtml?faces-redirect=true";
    }
    public String deletarLocal(int ID){
        System.out.println(ID);

        localService.deletarLocal(ID);
        return "/restrito/local/locaisList.xhtml?faces-redirect=true";
    }


    public List<Local> listarLocais() {
        return localService.listarLocais();
    }



    public String editarLocal(Local local) {
        nome = local.getNome();
        CNPJ = local.getCNPJ();
        endereco = local.getEndereco();
        IDString = String.valueOf(local.getID());
        localService.editarLocal(local);
        return "/restrito/local/locaisList.xhtml?faces-redirect=true";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}