package com.banana.DAO.Impl;

import com.banana.DAO.LoginDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean verificarLoginPassword(String login, String senha) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Usuario WHERE login = :login AND senha = :senha");
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            int t = query.getResultList().size();
            if(t > 0){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

