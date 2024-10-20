package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Departamento;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by USER on 19/10/2024.
 */

public class DepartamentoDAOImpl extends GenericDAOImpl<Departamento> {
    private SessionFactory sessionFactory;

    public DepartamentoDAOImpl(SessionFactory sessionFactory) {
        super(Departamento.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Departamento departamento) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(departamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Departamento read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Departamento.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Departamento> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Departamento", Departamento.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Departamento departamento) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(departamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Departamento departamento = session.get(Departamento.class, id);
            if (departamento != null) {
                session.delete(departamento);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
