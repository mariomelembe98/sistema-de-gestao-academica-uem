package com.uem.sgnfx.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import java.util.List;

public class GenericDAOImpl<T> implements GenericDAO<T> {

    private Class<T> entityClass;
    private SessionFactory sessionFactory;

    // Construtor que recebe a classe da entidade e a SessionFactory do Hibernate
    public GenericDAOImpl(Class<T> entityClass, SessionFactory sessionFactory) {
        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);  // Hibernate salva a entidade no banco de dados
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<T> readAll() {
        try (Session session = sessionFactory.openSession()) {
            // Usa HQL para buscar todas as entidades
            return session.createQuery("from " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);  // Hibernate atualiza a entidade no banco de dados
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
            // Busca a entidade pelo ID e a remove
            T entity = session.find(entityClass, id);
            if (entity != null) {
                session.delete(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
