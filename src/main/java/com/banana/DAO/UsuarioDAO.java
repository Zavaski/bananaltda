package com.banana.DAO;

import com.banana.Model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.banana.DAO.HibernateUtil;
import com.banana.Model.Usuario;

import java.util.List;

public class UsuarioDAO {

    public void criarUsuario(Usuario user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<Usuario> listarUsuarios(){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List <Usuario> users = session.createQuery("from Usuario ", Usuario.class).list();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public void deletarUsuario(Usuario user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.delete(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void editarUsuario(Usuario user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.update(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}