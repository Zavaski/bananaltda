package com.banana.DAO.Impl;

import com.banana.DAO.ReservaSalaDAO;
import com.banana.Model.ReservaSala;
import com.banana.Model.Sala;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.context.FacesContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReservaSalaDAOImpl implements ReservaSalaDAO {

    @Override
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

    @Override
    public void editarSala(ReservaSala reservaSala) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(reservaSala);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<ReservaSala> listarReservaSalas() {
        Transaction transaction = null;
        List<ReservaSala> reservaSalas =  new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            reservaSalas = session.createQuery("from ReservaSala ", ReservaSala.class).list();
            return reservaSalas;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return reservaSalas;
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
            if (transaction != null) {
                transaction.rollback();
            }
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
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public boolean buscarReservaPeriodo(Date dataInicioQuery, Date dataFimQuery, int SalaID) {
        boolean dataInicialPeriodo = buscarReservaPeriodoDataInicial(dataInicioQuery, dataFimQuery,SalaID);
        boolean dataFimPeriodo = buscarReservaPeriodoDataFinal(dataFimQuery, SalaID);
        System.out.println("data inicio periodo: " + dataInicialPeriodo);
        System.out.println("data fim periodo: " + dataFimPeriodo);

        if(dataInicialPeriodo ||dataFimPeriodo){
            return true;
        }
        return false;
    }
    public boolean buscarReservaPeriodoDataInicial(Date dataInicioQuery,  Date dataFimQuery, int SalaID){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM ReservaSala AS rs WHERE rs.sala.id= :id AND rs.dataInicio BETWEEN :dataInicial AND :dataFinal ");

            query.setParameter("id", SalaID);
            query.setParameter("dataInicial", dataInicioQuery.toString());
            query.setParameter("dataFinal", dataFimQuery.toString());

            //   query.setParameter("dataFinal", dataFimQuery);
            System.out.println("data INICIO SIZE " + query.getResultList().size());
            if (query.getResultList().size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    public boolean buscarReservaPeriodoDataFinal(Date dataFimQuery, int SalaID){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM ReservaSala WHERE sala.id = :id AND :dataFimQuery >= dataInicio and :dataFimQuery <= dataFim");
            query.setParameter("id", SalaID);
            query.setParameter("dataFimQuery", dataFimQuery);
            System.out.println("data FIM SIZE " + query.getResultList().size());
            if(query.getResultList().size() > 0){
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

}
