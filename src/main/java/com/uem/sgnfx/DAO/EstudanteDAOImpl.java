package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Estudante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class EstudanteDAOImpl extends GenericDAOImpl<Estudante> {
    private SessionFactory sessionFactory;

    public EstudanteDAOImpl(SessionFactory sessionFactory) {
        super(Estudante.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public List<Estudante> buscarPorCriterioUnico(String criterio) {
        try (Session session = sessionFactory.openSession()) {
            String hql;

            if (criterio.contains("@")) {
                // Se contiver "@" é um endereço eletrónico
                hql = "from Estudante where email like :criterio";
            } else if (criterio.matches("\\d+")) {
                // Se for numérico, é um código de estudante
                hql = "from Estudante where codigoestudante like :criterio";
            } else {
                // Caso contrário, busque por nome ou apelido
                hql = "from Estudante where nome like :criterio or apelido like :criterio";
            }

            Query<Estudante> query = session.createQuery(hql, Estudante.class);
            query.setParameter("criterio", "%" + criterio + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Estudante> buscarPorCriterioCurso(Long cursoID) {
        try (Session session = sessionFactory.openSession()) {
            Query<Estudante> query = session.createQuery("FROM Estudante WHERE curso.id = :cursoID", Estudante.class);
            query.setParameter("cursoID", cursoID);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
    public List<Estudante> listarActivos() {
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

    /**
     * @param id
     */
    @Override
    public void read(Long id) {

    }
}
