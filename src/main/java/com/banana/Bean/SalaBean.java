package com.banana.Bean;
import com.banana.Model.Local;
import com.banana.Model.Sala;
import com.banana.Service.LocalService;
import com.banana.Service.SalaService;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.registry.infomodel.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;
@Named("salaBean")
@ViewScoped
public class SalaBean implements Serializable {
    private String nome;
    private String descricao;
    private int idLocal;
    List<SelectItem> locais;
    @Inject
    transient private SalaService salaService;
    @Inject
     transient private LocalService localService;

    public String salvarSala() {
        System.out.print("------------");
        System.out.println(idLocal);
        salaService.salvarSala(nome, descricao,idLocal);
        return "/restrito/sala/salasList.xhtml?faces-redirect=true";
    }
    public String editarSala(int ID){

        salaService.editarTela(ID);
        return "/restrito/sala/editSala.xhtml?faces-redirect=true";
    }
    public String deletarSala(int ID){
        System.out.println("DELETAR BEAN " );
        System.out.println(ID);

        salaService.deletarSala(ID);
        return "/salasList.xhtml?faces-redirect=true";
    }


    public List<Sala> listarSalas() {
        return salaService.listarSalas();
    }


    public String editarSala(Sala sala) {
        salaService.editarSala(sala);
        return "/restrito/sala/salasList.xhtml?faces-redirect=true";
    }

    public List<SelectItem> listarLocais() {
        locais = new ArrayList<SelectItem>();
        for (Local l : localService.listarLocais()) {
            locais.add(new SelectItem(l.getID(), l.getNome()));
        }
        return locais;
   }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public SalaService getSalaService() {
        return salaService;
    }

    public void setSalaService(SalaService salaService) {
        this.salaService = salaService;
    }
}