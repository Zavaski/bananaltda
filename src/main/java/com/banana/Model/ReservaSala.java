package com.banana.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ReservaSala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private Date dataInicio;
    private Date dataFim;
    @OneToOne
    private Local local;
    @OneToOne
    private Sala sala;
    @OneToOne
    private Usuario usuario;
    private boolean cafe;
    private int quantidadePessoas;
    private String descricao;

    public ReservaSala(){

    }

    public ReservaSala(Date dataInicio, Date dataFim, Local local, Sala sala, Usuario usuario, boolean cafe, int quantidadePessoas, String descricao) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        this.sala = sala;
        this.usuario = usuario;
        this.cafe = cafe;
        this.quantidadePessoas = quantidadePessoas;
        this.descricao = descricao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }


    public boolean isCafe() {
        return cafe;
    }

    public void setCafe(boolean cafe) {
        this.cafe = cafe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}