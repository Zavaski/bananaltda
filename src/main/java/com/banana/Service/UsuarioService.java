package com.banana.Service;

import com.banana.Model.Usuario;

import java.util.List;

public interface UsuarioService {

    public void cadastrarUsuario(String nome, String login, String senha, String email);

    public List<Usuario> listarUsuario();

    public boolean verificarExistsLogin(String login);

}
