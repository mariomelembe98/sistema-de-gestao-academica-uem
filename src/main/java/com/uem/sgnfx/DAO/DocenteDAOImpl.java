package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Departamento;
import com.uem.sgnfx.Models.Docente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by USER on 16/10/2024.
 */

public class DocenteDAOImpl extends GenericDAOImpl<Docente> {
    private SessionFactory sessionFactory;

    public DocenteDAOImpl(SessionFactory sessionFactory) {
        super(Docente.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    // Método específico: Buscar docentes por nome
    public List<Docente> buscarPorNome(String nome) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Docente where nome like :nome", Docente.class)
                    .setParameter("nome", "%" + nome + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método específico: Listar docentes ativos
    public List<Docente> listarAtivos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Docente where isActive = true", Docente.class).list();
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
    public Departamento read(Long id) {
        return null;
    }
}
