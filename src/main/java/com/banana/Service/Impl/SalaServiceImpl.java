package com.banana.Service.Impl;


import com.banana.DAO.Impl.SalaDAOImpl;
import com.banana.Model.Sala;
import com.banana.Service.SalaService;

import javax.inject.Inject;
import java.util.List;

public class SalaServiceImpl implements SalaService{
    @Inject
    transient private SalaDAOImpl salaDAO;
    @Inject
    transient private LocalServiceImpl localService;

    @Override
    public void salvarSala(String nome, String descricao, int IDLocal){
        Sala sala = new Sala();
        sala.setDescricao(descricao);
        sala.setNome(nome);
        sala.setLocal(localService.buscarLocalByID(IDLocal));
        cadastrarSala(sala);
    }


    @Override
    public void deletarSala(int ID){
        salaDAO.searchSalaEDeletePorID(ID);
    }

    @Override
    public void editarSala(int ID){
        System.out.println("ID no service: " + ID);
        salaDAO.searchLocalEditarPorID(ID);
    }

    @Override
    public void cadastrarSala(Sala sala) {
        salaDAO.criarSala(sala);
    }

    @Override
    public List<Sala> listarSalas() {
        return salaDAO.listarSalas();
    }

    @Override
    public void editarSala(Sala sala) {
        salaDAO.editarSala(sala);
    }

    @Override
    public List<Sala> listarSalasByLocalID(int LocalID) {
        return salaDAO.listarSalasByLocalID(LocalID);
    }

    @Override
    public Sala buscarSalaByID(int ID) {
        return salaDAO.buscarSalaByID(ID);

    }


}