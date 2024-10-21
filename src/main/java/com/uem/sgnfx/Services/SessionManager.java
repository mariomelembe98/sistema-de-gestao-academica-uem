package com.uem.sgnfx.Services;

import com.uem.sgnfx.Models.User;

/**
 * Created by USER on 21/10/2024.
 */

public class SessionManager {

    private static User loggedInUser;

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void clearSession() {
        loggedInUser = null;
    }

}
