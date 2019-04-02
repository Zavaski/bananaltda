package com.banana.DAO;

import com.banana.Model.ReservaSala;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

public class ReservaSalaDAO {

        public void criarReservaSala(ReservaSala reservaSala) {
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
