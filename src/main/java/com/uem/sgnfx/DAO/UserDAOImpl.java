package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.User;
import org.hibernate.SessionFactory;

/**
 * Created by USER on 16/10/2024.
 */

public class UserDAOImpl extends GenericDAOImpl<User> {
    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory){
        super(User.class, sessionFactory);
    }
}
