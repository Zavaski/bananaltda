package com.banana.Service.Impl;
import com.banana.DAO.Impl.UsuarioDAOImpl;
import com.banana.Model.Usuario;
import com.banana.Service.UsuarioService;

import javax.inject.Inject;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    Usuario usuario;
    @Inject
    transient private UsuarioDAOImpl usuarioDAO;

    @Override
    public void cadastrarUsuario(String nome, String login, String senha, String email) {
        usuario = new Usuario(nome,login, senha, email);
        usuarioDAO.criarUsuario(usuario);
    }

    @Override
    public List<Usuario> listarUsuario() {
        for (Usuario user : usuarioDAO.listarUsuarios()) {
            System.out.println(user.getEmail());
        }
        return usuarioDAO.listarUsuarios();
    }

    @Override
    public boolean verificarExistsLogin(String login) {
        return usuarioDAO.verificarExistsLogin(login);
    }


}