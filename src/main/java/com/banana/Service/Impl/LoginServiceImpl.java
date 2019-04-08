package com.banana.Service.Impl;

import com.banana.DAO.Impl.LoginDAOImpl;
import com.banana.Model.Local;
import com.banana.Service.LocalService;
import com.banana.Service.LoginService;

import java.util.List;

public class LoginServiceImpl implements LoginService {
    LoginDAOImpl loginDAO;

    @Override
    public boolean verificarLoginPassword(String login, String senha) {
        loginDAO = new LoginDAOImpl();
        return loginDAO.verificarLoginPassword(login, senha);
    }
}
