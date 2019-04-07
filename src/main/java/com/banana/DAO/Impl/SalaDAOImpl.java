package com.banana.DAO.Impl;

import com.banana.DAO.ReservaSalaDAO;
import com.banana.DAO.SalaDAO;
import com.banana.Model.Local;
import com.banana.Model.Sala;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.context.FacesContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalaDAOImpl implements SalaDAO {

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Sala> listarSalasByLocalID(int LocalID) {
        System.out.println("------------------------Local ID DAO");
        System.out.println(LocalID);
        System.out.println("------------------------Local ID DAO");
        Transaction transaction = null;
        List<Sala> salas = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Sala where local.id = :id ");
            query.setParameter("id", LocalID);
            salas = query.getResultList();
           // salas = session.createQuery("from Sala where Sala.local.ID = :locarID ", Sala.class).list();
            System.out.println("------------------------SIZE");
            System.out.println(salas.size());
            System.out.println("------------------------SIZE");

            return salas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Sala buscarSalaByID(int ID) {
        Transaction transaction = null;
        Sala sala;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            sala = (Sala) session.get(Sala.class, ID);
            return sala;
        } catch (Exception e) {
            e.printStackTrace();
            sala = new Sala();
            return sala;
        }
    }
}