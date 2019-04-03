package com.banana.DAO;

import com.banana.Model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    public void criarUsuario(Usuario user);

    public List<Usuario> listarUsuarios();

    public void deletarUsuario(Usuario user);

    public void editarUsuario(Usuario user);

}
