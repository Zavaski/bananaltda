package com.banana.Service.Impl;

import com.banana.DAO.Impl.LocalDAOImpl;
import com.banana.Model.Local;
import com.banana.Service.LocalService;

import javax.inject.Inject;
import java.util.List;

public class LocalServiceImpl implements LocalService {

    Local local;
    @Inject
    transient private LocalDAOImpl localDAO;

    public void salvarLocal(String nome, String CNPJ, String endereco){
        System.out.print("Salvar service ");
        Local local = new Local();
        local.setCNPJ(CNPJ);
        local.setEndereco(endereco);
        local.setNome(nome);
        cadastrarLocal(local);
    }

    @Override
    public void deletarLocal(int id){
        localDAO.searchLocalDeletePorID(id);
    }

    @Override
    public void editarTela(int ID){
        System.out.println("ID no service: " + ID);
        localDAO.searchLocalPorID(ID);
    }


    @Override
    public void cadastrarLocal(Local local) {
        localDAO.criarLocal(local);
    }

    @Override
    public List<Local> listarLocais() {
        return localDAO.listarLocais();
    }

    @Override
    public void editarLocal(Local local) {
        localDAO.editarLocal(local);
    }

    @Override
    public Local buscarLocalByID(int ID) {
        return localDAO.buscarLocalByID(ID);
    }


}