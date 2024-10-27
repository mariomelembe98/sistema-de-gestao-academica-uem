package com.uem.sgnfx.Services;

import com.uem.sgnfx.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

/**
 * Created by USER on 22/10/2024.
 */

public class LoginService {

    // Método genérico para login
    public <T> T login(Class<T> entityClass, String email, String password) {
        Transaction transaction = null;
        T user = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Busca a entidade pelo endereço eletrónico
            Query<T> query = session.createQuery("FROM " + entityClass.getSimpleName() + " WHERE email = :email", entityClass);
            query.setParameter("email", email);
            List<T> users = query.list();

            // Se encontrar a entidade, verifica a senha
            if (!users.isEmpty()) {
                user = users.get(0);

                // Acessa o campo da senha usando reflexão, garantindo que a entidade tenha o campo "senha"
                String storedPassword = (String) entityClass.getMethod("getPassword").invoke(user);

                // Verifica se a senha informada corresponde ao hash armazenado no banco
                if (BCrypt.checkpw(password, storedPassword)) {
                    transaction.commit();
                    return user; // Login bem-sucedido
                } else {
                    // Senha incorreta
                    user = null;
                }
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
}

