package com.banana.DAO;

import com.banana.Model.Local;

import java.util.List;

public interface LocalDAO {
    public void criarLocal(Local local);

    public List<Local> listarLocais();

    public void searchLocalDeletePorID(int ID);

    public void editarLocal(Local local);

    public void searchLocalPorID(int ID);

    public Local buscarLocalByID(int ID);

}
