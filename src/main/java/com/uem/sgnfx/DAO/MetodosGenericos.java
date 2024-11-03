package com.uem.sgnfx.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by USER on 25/10/2024.
 */

public class MetodosGenericos {

    private SessionFactory sessionFactory;

    public MetodosGenericos(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public <T> List<T> buscarPorCriterio(Class<T> entityClass, String campo, String criterio) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from " + entityClass.getSimpleName() + " where " + campo + " like :criterio";

            Query<T> query = session.createQuery(hql, entityClass);
            query.setParameter("criterio", "%" + criterio + "%");
            return query.list();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
