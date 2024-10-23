package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.User;
import com.uem.sgnfx.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserDAOImpl extends GenericDAOImpl<User> {
    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public User login(String email, String password) {
        Transaction transaction = null;
        User user = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Busca o utilizador pelo endereço eletrónico
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            List<User> users = query.list();

            // Se encontrar o usuário, verifica a senha
            if (!users.isEmpty()) {
                user = users.get(0);

                // Verifica se a senha informada corresponde ao hash armazenado no banco
                if (BCrypt.checkpw(password, user.getPassword())) {
                    transaction.commit();
                    return user; // Login bem-sucedido
                } else {
                    // Senha incorreta
                    user = null; // Define como null se a senha não corresponder
                }
            }

            transaction.commit(); // Commit da transação

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback em caso de erro
            }
            e.printStackTrace();
        }

        return user; // Retorna null se as credenciais forem inválidas
    }

    /**
     * @param id
     */
    @Override
    public void read(Long id) {

    }


    public void createUser(String name, String email, String password) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        create(user);

    }

}
