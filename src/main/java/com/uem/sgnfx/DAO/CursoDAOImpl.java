package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Curso;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public class CursoDAOImpl extends GenericDAOImpl<Curso> {

    private SessionFactory sessionFactory;

    public CursoDAOImpl(SessionFactory sessionFactory) {
        super(Curso.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Curso> readAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Curso d join fetch d.departamento", Curso.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Exemplo de um método específico para buscar cursos por nome
    public List<Curso> buscarPorNome(String nome) {
        try (Session session = sessionFactory.openSession()) {
            Query<Curso> query = session.createQuery("from Curso where nome like :nome", Curso.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Exemplo de outro método específico: buscar curso por código
    public Curso buscarPorCodigo(String id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Curso> query = session.createQuery("from Curso where id = :id", Curso.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

