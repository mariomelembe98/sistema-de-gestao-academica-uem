package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.Departamento;
import com.uem.sgnfx.Models.User;
import com.uem.sgnfx.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

/**
 * Created by USER on 16/10/2024.
 */

public class UserDAOImpl extends GenericDAOImpl<User> {
    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory){
        super(User.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public User login(String email, String password) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hashedPassword = hashPassword(password);

            Query<User> query = session.createQuery("FROM User WHERE email = :email AND password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", hashedPassword);

            List<User> users = query.list();
            if (!users.isEmpty()) {
                user = users.get(0);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    // Método de hashing de senha (igual ao anterior)
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash de senha", e);
        }
    }

    /**
     * @param id
     */
    @Override
    public void read(Long id) {

    }
}
