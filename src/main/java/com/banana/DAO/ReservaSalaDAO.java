package com.banana.DAO;

import com.banana.Model.ReservaSala;

import java.util.Date;
import java.util.List;

public interface ReservaSalaDAO {

    public void criarReservaSala(ReservaSala reservaSala);

    public void editarSala(ReservaSala reservaSala);

    public List<ReservaSala> listarReservaSalas();

    public void searchSalaEDeletePorID(int ID);

    public void searchLocalEditarPorID(int ID);

    public boolean buscarReservaPeriodo(Date dataInicioQuery, Date dataFimQuery, int SalaID);
}
