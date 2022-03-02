package com.epam.dao;

import com.epam.entities.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import java.util.Optional;

public class UserDAO {
    EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insert(User user) {
        boolean insertStatus = true;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            insertStatus = false;
            entityManager.getTransaction().rollback();
        }
        return insertStatus;
    }

    public Optional<User> delete(String userName) {
        Optional<User> userOptional = getUser(userName);
        if (userOptional.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(userOptional.get());
            entityManager.getTransaction().commit();
        }
        return userOptional;
    }

    public Optional<User> getUser(String userName) {
        return Optional.ofNullable((User) entityManager
                .createQuery("select user from User user where user.userName like :userName")
                .setParameter("userName", userName).getSingleResult());
    }
}
