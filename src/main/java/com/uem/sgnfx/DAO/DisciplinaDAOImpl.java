package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Departamento;
import com.uem.sgnfx.Models.Disciplina;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class DisciplinaDAOImpl extends GenericDAOImpl<Disciplina> {

    private SessionFactory sessionFactory;

    public DisciplinaDAOImpl(SessionFactory sessionFactory) {
        super(Disciplina.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Departamento read(Long id) {
        return null;
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
