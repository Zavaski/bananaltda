package com.banana.Service;

import com.banana.Model.Sala;

import java.util.List;

public interface SalaService {

    public void salvarSala(String nome, String descricao, int IDLocal);

    public void deletarSala(int id);

    public void editarTela(int ID);

    public void cadastrarSala(Sala sala);

    public List<Sala> listarSalas();

    public void editarSala(Sala sala);

}
