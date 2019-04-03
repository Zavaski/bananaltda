package com.banana.DAO;

import com.banana.Model.Sala;

import java.util.List;

public interface SalaDAO {

    public void criarSala(Sala sala);

    public void editarSala(Sala sala);

    public List<Sala> listarSalas();

    public void searchSalaEDeletePorID(int ID);

    public void searchLocalEditarPorID(int ID) ;
}
