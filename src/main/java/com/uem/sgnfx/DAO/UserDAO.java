package com.uem.sgnfx.DAO;

import com.uem.sgnfx.Models.User;

import java.util.List;

public interface UserDAO {
    List<User> readAll();
    void create(User user);
}
