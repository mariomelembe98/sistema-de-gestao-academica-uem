package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Nota;
import com.uem.sgnfx.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class NotaDAO extends GenericDAOImpl<Nota>{

    private SessionFactory sessionFactory;

    public NotaDAO(SessionFactory sessionFactory) {
        super(Nota.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    // CREATE - Adicionar uma nova Nota
    @Override
    public Nota create(Nota nota) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(nota);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return nota;
    }

    // READ - Obter uma Nota pelo ID
    @Override
    public Nota read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Nota.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // READ - Obter todas as Notas
    @Override
    public List<Nota> readAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Nota", Nota.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // UPDATE - Atualizar uma Nota
    @Override
    public void update(Nota nota) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(nota);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // DELETE - Excluir uma Nota
    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Nota nota = session.get(Nota.class, id);
            if (nota != null) {
                transaction = session.beginTransaction();
                session.delete(nota);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
