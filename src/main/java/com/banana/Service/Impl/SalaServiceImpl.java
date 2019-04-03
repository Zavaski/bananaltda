package com.banana.Service.Impl;


import com.banana.DAO.Impl.SalaDAOImpl;
import com.banana.Model.Sala;
import com.banana.Service.SalaService;

import javax.inject.Inject;
import java.util.List;

public class SalaServiceImpl implements SalaService {
    @Inject
    transient private SalaDAOImpl salaDAO;
    @Inject
    transient private LocalServiceImpl localService;

    public void salvarSala(String nome, String descricao, int IDLocal){
        System.out.print("Salvar service ");
        Sala sala = new Sala();
        sala.setDescricao(descricao);
        sala.setNome(nome);
        cadastrarSala(sala);
    }


    public void deletarSala(int id){
        salaDAO.searchSalaEDeletePorID(id);
    }

    public void editarTela(int ID){
        System.out.println("ID no service: " + ID);
        salaDAO.searchLocalEditarPorID(ID);
    }


    public void cadastrarSala(Sala sala) {
        salaDAO.criarSala(sala);
    }
    public List<Sala> listarSalas() {
        return salaDAO.listarSalas();
    }


    public void editarSala(Sala sala) {
        salaDAO.editarSala(sala);
    }


}