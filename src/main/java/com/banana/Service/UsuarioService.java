package com.banana.Service;
import com.banana.DAO.UsuarioDAO;
import com.banana.Model.Usuario;

import javax.inject.Inject;
import java.util.List;

public class UsuarioService {
    Usuario usuario;
    @Inject
    transient private UsuarioDAO usuarioDAO;

    public void cadastrarUsuario(String nome, String login, String senha, String email) {
        usuario = new Usuario(nome,login, senha, email);
        usuarioDAO.criarUsuario(usuario);
    }
    public List<Usuario> listarUsuario() {
        for (Usuario user : usuarioDAO.listarUsuarios()) {
            System.out.println(user.getEmail());
        }
        return usuarioDAO.listarUsuarios();
    }
}