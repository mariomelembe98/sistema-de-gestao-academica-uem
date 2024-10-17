package com.uem.sgnfx.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Configura e constrói o SessionFactory a partir do hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Falha ao inicializar o SessionFactory.");
        }
    }

    // Método para obter o SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Método para fechar o SessionFactory ao finalizar o aplicativo
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
