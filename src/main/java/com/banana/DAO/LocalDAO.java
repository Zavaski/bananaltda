package com.banana.DAO;

import com.banana.Model.Local;
import com.banana.Model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.banana.DAO.HibernateUtil;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocalDAO {

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

}