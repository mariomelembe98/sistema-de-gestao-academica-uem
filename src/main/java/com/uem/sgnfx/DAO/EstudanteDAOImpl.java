package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Estudante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import java.util.List;

public class EstudanteDAOImpl implements EstudanteDAO {
    private SessionFactory sessionFactory;

    public EstudanteDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Estudante> readAll() {
        List<Estudante> estudantes = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Estudante> query = builder.createQuery(Estudante.class);
            query.from(Estudante.class);

            estudantes = session.createQuery(query).getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return estudantes != null ? estudantes : List.of();
    }

    public void create(Estudante estudante) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(estudante);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Estudante read(Long id) {
        Transaction transaction = null;
        Estudante estudante = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            estudante = session.get(Estudante.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return estudante;
    }

    public void update(Estudante estudante) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(estudante);
            transaction.commit();  // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback the transaction in case of an error
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Estudante estudante = session.get(Estudante.class, id);
            if (estudante != null) {
                session.delete(estudante);
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
