package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Turma;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public class TurmaDAOImpl extends GenericDAOImpl<Turma> {

    private SessionFactory sessionFactory;

    public TurmaDAOImpl(SessionFactory sessionFactory) {
        super(Turma.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Turma> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Turma d join fetch d.curso", Turma.class).list();
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


    // TODO: Método específico para buscar turmas por nome
    public List<Turma> buscarPorNome(String nome) {
        try (Session session = sessionFactory.openSession()) {
            Query<Turma> query = session.createQuery("from Turma where nome like :nome", Turma.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Método específico para buscar turmas por curso
    public List<Turma> buscarPorCurso(Long cursoId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Turma> query = session.createQuery("from Turma where curso.id = :cursoId", Turma.class);
            query.setParameter("cursoId", cursoId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
