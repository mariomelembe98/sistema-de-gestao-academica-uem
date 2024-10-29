package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Curso;
import com.uem.sgnfx.Models.Estudante;
import com.uem.sgnfx.Models.User;
import com.uem.sgnfx.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class EstudanteDAOImpl extends GenericDAOImpl<Estudante> {
    private SessionFactory sessionFactory;

    public EstudanteDAOImpl(SessionFactory sessionFactory) {
        super(Estudante.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
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

    public void createEstudante(String nome, String apelido, String email, String telefone, String codigoestudante, String endereco, String bi, String genero, LocalDate dataNascimento, String estadoCivil, String nacionalidade, String naturalidade, User user, Curso curso, Boolean isActive, Boolean isAdmin, String password, Instant createdAt, Instant updatedAt) {
        Estudante estudante = new Estudante();
        estudante.setNome(nome);
        estudante.setApelido(apelido);
        estudante.setEmail(email);
        estudante.setTelefone(telefone);
        estudante.setCodigoEstudante(codigoestudante);
        estudante.setEndereco(endereco);
        estudante.setBi(bi);
        estudante.setGenero(genero);
        estudante.setDataNascimento(dataNascimento);
        estudante.setEstadoCivil(estadoCivil);
        estudante.setNacionalidade(nacionalidade);
        estudante.setNaturalidade(naturalidade);
        estudante.setUser(user);
        estudante.setCurso(curso);
        estudante.setIsActive(isActive);
        estudante.setIsAdmin(isAdmin);
        estudante.setPassword(password);
        estudante.setCreatedAt(createdAt);
        estudante.setUpdatedAt(updatedAt);
        create(estudante);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Estudante read(Long id) {
        Estudante estudante = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            estudante = session.get(Estudante.class, id); // Carrega o estudante com o ID fornecido
        } catch (Exception e) {
            e.printStackTrace(); // Exibe o erro, se houver
        }
        return estudante;
    }

    public Long obterUltimoId() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (Long) session.createQuery("SELECT COALESCE(MAX(e.id), 0) FROM Estudante e").uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L; // Retorna 0 se não houver nenhum registro
        }
    }


}
