package com.banana.Service;

import com.banana.Model.ReservaSala;

import java.util.Date;
import java.util.List;

public interface ReservaSalaService {
        public void salvarReservaSala(Date dataInicio, Date dataFim, Date horaInicio, Date horaFim, int IDLocal, int IDSala, boolean cafe, int quantidadePessoas, String descricao);

        public void deletarReservaSala(int id);

        public void editarTela(int ID);

        public void cadastrarReservaSala(ReservaSala reservaSala);

        public void editarReservaSala(ReservaSala reservaSala);

        public List<ReservaSala> listarReservaSalas();

        public boolean buscarReservaPeriodo(Date dataInicioQuery, Date dataFimQuery, int SalaID);

}
