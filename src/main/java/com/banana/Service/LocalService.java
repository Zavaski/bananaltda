package com.banana.Service;

import com.banana.Model.Local;

import java.util.List;

public interface LocalService {

    public void salvarLocal(String nome, String CNPJ, String endereco);

    public void deletarLocal(int ID);

    public void editarTela(int ID);

    public void cadastrarLocal(Local local);

    public List<Local> listarLocais();

    public void editarLocal(Local local);

    public Local buscarLocalByID(int ID);
}
