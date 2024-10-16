package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Estudante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EstudanteDAOImpl extends GenericDAOImpl<Estudante> {
    private SessionFactory sessionFactory;

    public EstudanteDAOImpl(SessionFactory sessionFactory) {
        super(Estudante.class, sessionFactory);
    }

    // Todo: Método específico: Buscar estudantes por nome
    public List<Estudante> buscarPorNome(String nome) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Estudante where nome like :nome", Estudante.class)
                    .setParameter("nome", "%" + nome + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Método específico: Listar estudantes activos
    public List<Estudante> listarAtivos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Estudante where isActive = true", Estudante.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Método específico: Buscar estudantes por curso
    public List<Estudante> buscarPorCurso(Long cursoId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Estudante where curso.id = :cursoId", Estudante.class)
                    .setParameter("cursoId", cursoId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Método específico: Contar número total de estudantes
    public Long contarTotalDeEstudantes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(e) from Estudante e", Long.class).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
