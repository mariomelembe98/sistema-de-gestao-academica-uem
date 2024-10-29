package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Curso;
import com.uem.sgnfx.Models.Disciplina;
import com.uem.sgnfx.Models.Semestre;
import com.uem.sgnfx.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.Instant;
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
     * @return
     */
    @Override
    public Disciplina read(Long id) {

        return null;
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

    public void createDisciplina(String designacao, Curso curso, Semestre semestre) {
        Disciplina disciplina = new Disciplina();
        disciplina.setDesignacao(designacao);
        disciplina.setCreatedAt(Instant.now());
        disciplina.setUpdatedAt(Instant.now());
        disciplina.setCurso(curso);
        disciplina.setSemestre(semestre);
        create(disciplina);
    }

    public List<Disciplina> getDisciplinasPorDocente(Long docenteId) {
        List<Disciplina> disciplinas = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "SELECT d FROM Disciplina d " +
                    "JOIN DisciplinaDocente dd ON d.id = dd.disciplina.id " +
                    "WHERE dd.docente.id = :docenteId";

            Query<Disciplina> query = session.createQuery(hql, Disciplina.class);
            query.setParameter("docenteId", docenteId);

            disciplinas = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return disciplinas;
    }

    public List<Disciplina> findByDesignacao(String designacao) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Disciplina where designacao = :designacao", Disciplina.class)
                    .setParameter("designacao", designacao)
                    .getResultList();
        }
    }

}
