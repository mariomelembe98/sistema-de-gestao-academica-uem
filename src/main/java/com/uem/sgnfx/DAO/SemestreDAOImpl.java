package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Departamento;
import com.uem.sgnfx.Models.Semestre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by USER on 27/10/2024.
 */

public class SemestreDAOImpl extends GenericDAOImpl<Semestre>{

    private SessionFactory sessionFactory;

    public SemestreDAOImpl(SessionFactory sessionFactory) {
        super(Semestre.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Semestre semestre) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(semestre);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Semestre> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Semestre", Semestre.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Semestre read(Long id) {

        return null;
    }

    @Override
    public void update(Semestre semestre) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(semestre);
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
            Semestre semestre = session.get(Semestre.class, id);
            if (semestre != null) {
                session.delete(semestre);
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
