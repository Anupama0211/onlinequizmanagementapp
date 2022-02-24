package com.epam.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GetManager {
    private static EntityManager entityManager = null;

    private GetManager() {
    }

    public static EntityManager getEntityManger() {
        if (entityManager == null) {
            synchronized (GetManager.class) {
                if (entityManager == null) {
                    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-local-mysql");
                    entityManager = entityManagerFactory.createEntityManager();
                }
            }
        }
        return entityManager;
    }
}
