package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Avaliacao;
import com.uem.sgnfx.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AvaliacaoDAO extends GenericDAOImpl<Avaliacao>{
    private SessionFactory sessionFactory;
    public AvaliacaoDAO(SessionFactory sessionFactory) {
        super(Avaliacao.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    // CREATE - Adicionar uma nova Avaliação
    @Override
    public Avaliacao create(Avaliacao avaliacao) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(avaliacao);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    // READ - Obter uma Avaliação pelo ID
    public Avaliacao getAvaliacaoById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Avaliacao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // READ - Obter todas as Avaliações
    @Override
    public List<Avaliacao> readAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Avaliacao", Avaliacao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // UPDATE - Atualizar uma Avaliação
    @Override
    public void update(Avaliacao avaliacao) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(avaliacao);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // DELETE - Excluir uma Avaliação
    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Avaliacao avaliacao = session.get(Avaliacao.class, id);
            if (avaliacao != null) {
                transaction = session.beginTransaction();
                session.delete(avaliacao);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Avaliacao> getAvaliacoesPorDisciplina(Long disciplinaId) {
        List<Avaliacao> avaliacoes = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Criar a consulta para buscar as avaliações por disciplina
            Query<Avaliacao> query = session.createQuery("FROM Avaliacao WHERE disciplina.id = :disciplinaId", Avaliacao.class);
            query.setParameter("disciplinaId", disciplinaId);
            avaliacoes = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avaliacoes;
    }


}
