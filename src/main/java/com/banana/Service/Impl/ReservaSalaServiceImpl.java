package com.banana.Service.Impl;

import com.banana.DAO.Impl.ReservaSalaDAOImpl;
import com.banana.Model.ReservaSala;
import com.banana.Service.ReservaSalaService;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class ReservaSalaServiceImpl implements ReservaSalaService {
    @Inject
    transient private ReservaSalaDAOImpl reservaSalaDAO;
    @Inject
    transient private LocalServiceImpl localService;

    public void salvarReservaSala(Date dataInicio, Date dataFim, Date horaInicio, Date horaFim, int IDLocal, int IDSala, boolean cafe, int quantidadePessoas, String descricao){
        System.out.print("Salvar service ");
        ReservaSala rs = new ReservaSala();
        rs.setCafe(cafe);
        rs.setDataFim(dataFim);
        rs.setDataInicio(dataInicio);
        rs.setDescricao(descricao);
        rs.setQuantidadePessoas(quantidadePessoas);
        rs.setHoraFim(horaFim);
        rs.setHoraInicio(horaInicio);
        cadastrarReservaSala(rs);
    }


    public void deletarReservaSala(int id){
        reservaSalaDAO.searchSalaEDeletePorID(id);
    }

    public void editarTela(int ID){
        System.out.println("ID no service: " + ID);
        reservaSalaDAO.searchLocalEditarPorID(ID);
    }


    public void cadastrarReservaSala(ReservaSala reservaSala) {
        reservaSalaDAO.criarReservaSala(reservaSala);
    }
    public List<ReservaSala> listarReservaSalas() {
        return reservaSalaDAO.listarReservaSalas();
    }


    public void editarReservaSala(ReservaSala reservaSala) {
        reservaSalaDAO.editarSala(reservaSala);
    }


}
