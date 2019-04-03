package com.banana.DAO.Impl;

import com.banana.DAO.ReservaSalaDAO;
import com.banana.DAO.SalaDAO;
import com.banana.Model.Sala;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

public class SalaDAOImpl implements SalaDAO {
    public void criarSala(Sala sala) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(sala);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void editarSala(Sala sala) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(sala);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Sala> listarSalas() {
        Transaction transaction = null;
        List<Sala> salas;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            salas = session.createQuery("from Sala ", Sala.class).list();
            return salas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void searchSalaEDeletePorID(int ID) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Sala sala = (Sala) session.get(Sala.class, ID);
            session.delete(sala);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchLocalEditarPorID(int ID) {
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Sala sala = (Sala) session.get(Sala.class, ID);
            sessionMapObj.put("editSala", sala);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}