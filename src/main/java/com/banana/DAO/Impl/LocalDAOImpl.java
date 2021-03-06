package com.banana.DAO.Impl;

import com.banana.DAO.LocalDAO;
import com.banana.Model.Local;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

public class LocalDAOImpl implements LocalDAO {

    @Override
    public void criarLocal(Local local) {
        System.out.println("Cadastrar Local DAO");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(local);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Local> listarLocais(){
        System.out.println("Listar Local DAO");

        Transaction transaction = null;
        List <Local> locais;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            locais = session.createQuery("from Local ", Local.class).list();
            return locais;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void searchLocalDeletePorID(int ID){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Local local =  (Local) session.get(Local.class, ID);
            session.delete(local);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editarLocal(Local local) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(local);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchLocalPorID(int ID){
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Local local =  (Local) session.get(Local.class, ID);
            sessionMapObj.put("editLocal", local);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Local buscarLocalByID(int ID) {
        Transaction transaction = null;
        Local local;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            local = (Local) session.get(Local.class, ID);
            return local;
        } catch (Exception e) {
            e.printStackTrace();
            local = new Local();
            return local;
        }
    }
}