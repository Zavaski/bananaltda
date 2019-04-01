package com.banana.Service;


import com.banana.DAO.SalaDAO;
import com.banana.Model.Sala;

import javax.inject.Inject;
import java.util.List;

public class SalaService {
    @Inject
    transient private SalaDAO salaDAO;
    @Inject
    transient private LocalService localService;

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