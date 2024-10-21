package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Disciplina;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DisciplinaDAOImpl extends GenericDAOImpl<Disciplina> {

    private SessionFactory sessionFactory;

    public DisciplinaDAOImpl(SessionFactory sessionFactory) {
        super(Disciplina.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Disciplina disciplina) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(disciplina);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Disciplina> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Disciplina d join fetch d.curso", Disciplina.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param id
     */
    @Override
    public void read(Long id) {

    }

    public List<Disciplina> buscarPorNome(String designacao) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Disciplina where designacao like :designacao", Disciplina.class)
                    .setParameter("designacao", "%" + designacao + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
