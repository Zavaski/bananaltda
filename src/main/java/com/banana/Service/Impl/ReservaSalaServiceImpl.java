package com.banana.Service.Impl;

import com.banana.DAO.Impl.ReservaSalaDAOImpl;
import com.banana.Model.Local;
import com.banana.Model.ReservaSala;
import com.banana.Model.Sala;
import com.banana.Service.ReservaSalaService;
import com.sun.xml.bind.v2.model.core.ID;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class ReservaSalaServiceImpl implements ReservaSalaService {
    @Inject
    transient private ReservaSalaDAOImpl reservaSalaDAO;
    @Inject
    transient private LocalServiceImpl localService;
    @Inject
    transient private SalaServiceImpl salaService;

    @Override
    public void salvarReservaSala(Date dataInicio, Date dataFim, Date horaInicio, Date horaFim, int IDLocal, int IDSala, boolean cafe, int quantidadePessoas, String descricao){
        System.out.println("Salvar service ");
        System.out.println("IDLOCAL " + IDLocal);
        System.out.println("IDSALA " + IDSala);

        System.out.println("Salvar service ");
        dataInicio.setHours(horaInicio.getHours());
        dataInicio.setMinutes(horaInicio.getMinutes());
        dataFim = new Date();
        dataFim.setDate(dataInicio.getDate());
        dataFim.setHours(horaFim.getHours());
        dataFim.setMinutes(horaFim.getMinutes());
        Local local =  localService.buscarLocalByID(IDLocal);
        Sala sala = salaService.buscarSalaByID(IDSala);
        ReservaSala rs = new ReservaSala();
        rs.setHoraInicio(horaInicio);
        rs.setHoraFim(horaFim);
        rs.setCafe(cafe);
        rs.setDataFim(dataFim);
        rs.setDataInicio(dataInicio);
        rs.setDescricao(descricao);
        rs.setQuantidadePessoas(quantidadePessoas);
        rs.setLocal(local);
        rs.setSala(sala);
        cadastrarReservaSala(rs);
    }


    @Override
    public void deletarReservaSala(int id){
        reservaSalaDAO.searchSalaEDeletePorID(id);
    }

    @Override
    public void editarTela(int ID){
        System.out.println("ID no service: " + ID);
        reservaSalaDAO.searchLocalEditarPorID(ID);
    }


    @Override
    public void cadastrarReservaSala(ReservaSala reservaSala) {
        reservaSalaDAO.criarReservaSala(reservaSala);
    }

    @Override
    public List<ReservaSala> listarReservaSalas() {
        return reservaSalaDAO.listarReservaSalas();
    }

    @Override
    public boolean buscarReservaPeriodo(Date dataInicioQuery, Date dataFimQuery, int SalaID, Date horaInicio, Date horaFim) {
        dataInicioQuery.setHours(horaInicio.getHours());
        dataInicioQuery.setMinutes(horaInicio.getMinutes());
        dataFimQuery = new Date();
        dataFimQuery.setDate(dataInicioQuery.getDate());
        dataFimQuery.setHours(horaFim.getHours());
        dataFimQuery.setMinutes(horaFim.getMinutes());
        return reservaSalaDAO.buscarReservaPeriodo(dataInicioQuery,dataFimQuery,SalaID);
    }


    @Override
    public void editarReservaSala(ReservaSala reservaSala) {
        reservaSalaDAO.editarSala(reservaSala);
    }


}
