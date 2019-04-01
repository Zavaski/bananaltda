package com.banana.Service;

import com.banana.DAO.LocalDAO;
import com.banana.Model.Local;

import javax.inject.Inject;
import java.util.List;

public class LocalService {

    Local local;
    @Inject
    transient private LocalDAO localDAO;

    public void salvarLocal(String nome, String CNPJ, String endereco){
        System.out.print("Salvar service ");
        Local local = new Local();
        local.setCNPJ(CNPJ);
        local.setEndereco(endereco);
        local.setNome(nome);
        cadastrarLocal(local);
    }


    public void deletarLocal(int id){
        localDAO.searchLocalDeletePorID(id);
    }

    public void editarTela(int ID){
        System.out.println("ID no service: " + ID);
        localDAO.searchLocalPorID(ID);
    }


    public void cadastrarLocal(Local local) {
        localDAO.criarLocal(local);
    }
    public List<Local> listarLocais() {
        return localDAO.listarLocais();
    }


    public void editarLocal(Local local) {
        localDAO.editarLocal(local);
    }

}