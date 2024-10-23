package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Inscricao;
import com.uem.sgnfx.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class InscricaoDAOImpl implements GenericDAO<Inscricao> {

    private SessionFactory sessionFactory;

    public InscricaoDAOImpl(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }


    // Método para criar uma inscrição
    @Override
    public void create(Inscricao inscricao) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(inscricao);  // Salva a inscrição no banco
            transaction.commit();     // Confirma a transação
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Desfaz a transação em caso de erro
            }
            e.printStackTrace();
        }
    }

    // Método para listar todas as inscrições
    @Override
    public List<Inscricao> readAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Inscricao", Inscricao.class).list();
        }
    }

    /**
     * @param id
     */
    @Override
    public void read(Long id) {

    }


    // Método para atualizar uma inscrição existente
    @Override
    public void update(Inscricao inscricao) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(inscricao);  // Atualiza a inscrição
            transaction.commit();       // Confirma a transação
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Desfaz a transação em caso de erro
            }
            e.printStackTrace();
        }
    }

    // Método para deletar uma inscrição pelo ‘ID’
    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Inscricao inscricao = session.get(Inscricao.class, id);
            if (inscricao != null) {
                session.delete(inscricao);  // Deleta a inscrição
                transaction.commit();       // Confirma a transação
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Desfaz a transação em caso de erro
            }
            e.printStackTrace();
        }
    }

    // Método específico para buscar inscrições por curso
    public List<Inscricao> buscarPorDisciplina(Long disciplinaId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Inscricao> query = session.createQuery("from Inscricao where disciplina.id = :cursoId", Inscricao.class);
            query.setParameter("disciplinaId", disciplinaId);
            return query.list();
        }
    }

    // Método específico para buscar inscrições por estudante
    public List<Inscricao> buscarPorEstudante(Long estudanteId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Inscricao> query = session.createQuery("from Inscricao where estudante.id = :estudanteId", Inscricao.class);
            query.setParameter("estudanteId", estudanteId);
            return query.list();
        }
    }

    public List<Inscricao> getInscricoesPorDocente(Long docenteId) {
        List<Inscricao> inscricoes = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "SELECT i FROM Inscricao i " +
                    "JOIN i.disciplina d " +
                    "JOIN DisciplinaDocente dd ON d.id = dd.disciplina.id " +
                    "WHERE dd.docente.id = :docenteId";

            Query<Inscricao> query = session.createQuery(hql, Inscricao.class);
            query.setParameter("docenteId", docenteId);

            inscricoes = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return inscricoes;
    }

    public List<Inscricao> getInscricoesPorDisciplina(Long disciplinaId) {
        List<Inscricao> inscricoes = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Inscricao WHERE disciplina.id = :disciplinaId";
            Query<Inscricao> query = session.createQuery(hql, Inscricao.class);
            query.setParameter("disciplinaId", disciplinaId);

            inscricoes = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return inscricoes;
    }


}
