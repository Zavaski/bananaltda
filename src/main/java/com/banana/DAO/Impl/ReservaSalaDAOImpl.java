package com.banana.DAO.Impl;

import com.banana.DAO.ReservaSalaDAO;
import com.banana.Model.ReservaSala;
import com.banana.Model.Sala;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sun.rmi.runtime.Log;

import javax.faces.context.FacesContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;

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
        //boolean dataInicialPeriodo = buscarReservaPeriodoDataInicial(dataInicioQuery, dataFimQuery, SalaID);
        return buscarReservaPeriodoDataInicial(dataInicioQuery, dataFimQuery, SalaID);
    }
    public boolean buscarReservaPeriodoDataInicial(Date dataInicioQuery, Date dataFimQuery, int SalaID){
        Transaction transaction = null;
        java.sql.Date dataInicio ;
        dataInicio = new java.sql.Date(dataInicioQuery.getTime());
        java.sql.Date dataFim;
        dataFim = new java.sql.Date(dataFimQuery.getTime());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            //SELECT * FROM banana.reservasala as rs WHERE (sala_id = 8) AND ( (dataInicio BETWEEN '2019-04-01 00:02:00' AND '2019-04-01 15:40:00') );
            Query query = session.createQuery("FROM ReservaSala AS rs WHERE (rs.sala.id= :id) AND rs.dataInicio BETWEEN :dataInicial AND :dataFinal");
            query.setParameter("id", SalaID);
            query.setParameter("dataInicial", dataInicio);
            query.setParameter("dataFinal",dataFim);

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
//    public boolean buscarReservaPeriodoDataFinal(Date dateInicioQuery, Date dataFimQuery, int SalaID){
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query query = session.createQuery("FROM ReservaSala WHERE sala.id = :id AND :dataFimQuery >= dataInicio and :dataFimQuery <= dataFim");
//            query.setParameter("id", SalaID);
//            query.setParameter("dataFimQuery", dataFimQuery);
//            System.out.println("data FIM SIZE " + query.getResultList().size());
//            if(query.getResultList().size() > 0){
//                return true;
//            }
//            return false;
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            return false;
//        }
//    }

}
