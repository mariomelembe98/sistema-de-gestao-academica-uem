package com.uem.sgnfx.Services;

public class SessionManager<T> {

    private static Object loggedInEntity;

    // Define a entidade logada
    public static <T> void setLoggedInEntity(T entity) {
        loggedInEntity = entity;
    }

    // Retorna a entidade logada
    @SuppressWarnings("unchecked")
    public static <T> T getLoggedInEntity() {
        return (T) loggedInEntity;
    }

    // Limpa a sessão
    public static void clearSession() {
        loggedInEntity = null;
    }
}
