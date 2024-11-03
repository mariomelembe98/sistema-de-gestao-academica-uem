package com.uem.sgnfx.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.uem.sgnfx.Models.Regime;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by USER on 31/10/2024.
 */

public class RegimeDAOImpl extends GenericDAOImpl<Regime>{

    private SessionFactory sessionFactory;

    public RegimeDAOImpl(SessionFactory sessionFactory) {
        super(Regime.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Regime create(Regime regime) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(regime);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Regime> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Regime ", Regime.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
