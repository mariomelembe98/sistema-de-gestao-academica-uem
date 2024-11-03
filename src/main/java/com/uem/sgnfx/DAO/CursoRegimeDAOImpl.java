package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Curso;
import com.uem.sgnfx.Models.CursoRegime;
import com.uem.sgnfx.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by USER on 31/10/2024.
 */

public class CursoRegimeDAOImpl extends GenericDAOImpl<CursoRegime>{
    private SessionFactory sessionFactory;
    public CursoRegimeDAOImpl(SessionFactory sessionFactory) {
        super(CursoRegime.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CursoRegime create(CursoRegime cursoRegime) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cursoRegime);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

}
