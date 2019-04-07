package com.banana.DAO.Impl;

import com.banana.DAO.ReservaSalaDAO;
import com.banana.Model.ReservaSala;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

public class ReservaSalaDAOImpl implements ReservaSalaDAO {

    @Override
    public void criarReservaSala(ReservaSala reservaSala) {
            System.out.println("----------------- RS DAO");
            System.out.println("Reserva memory " + reservaSala);
            System.out.println("----------------- RS DAO");
            Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(reservaSala);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void editarSala(ReservaSala reservaSala) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(reservaSala);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ReservaSala> listarReservaSalas() {
        Transaction transaction = null;
        List<ReservaSala> reservaSalas;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            reservaSalas = session.createQuery("from ReservaSala ", ReservaSala.class).list();
            return reservaSalas;
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
            ReservaSala reservaSalas = (ReservaSala) session.get(ReservaSala.class, ID);
            session.delete(reservaSalas);
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
            ReservaSala reservaSala = (ReservaSala) session.get(ReservaSala.class, ID);
            sessionMapObj.put("editReservaSala", reservaSala);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
