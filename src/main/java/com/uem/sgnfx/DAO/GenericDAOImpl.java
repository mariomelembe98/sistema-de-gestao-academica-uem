package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Nota;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import java.util.List;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private Class<T> entityClass;
    protected SessionFactory sessionFactory;

    public GenericDAOImpl(Class<T> entityClass, SessionFactory sessionFactory) {
        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T create(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> readAll() {
        try (Session session = sessionFactory.openSession()) {
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
            session.update(entity);
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

    @Override
    public long count() {
        try (Session session = sessionFactory.openSession()) {
            return (long) session.createQuery("SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e")
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Retorna 0 em caso de erro
        }
    }

    // CREATE - Adicionar uma nova Nota

}
