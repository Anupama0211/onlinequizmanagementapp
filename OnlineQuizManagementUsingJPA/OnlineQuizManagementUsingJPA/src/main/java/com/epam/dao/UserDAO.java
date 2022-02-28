package com.epam.dao;

import com.epam.entities.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    EntityManager entityManager = GetManager.getEntityManger();

    public boolean insert(User user) {
        boolean insertStatus = true;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            insertStatus = false;
        }
        return insertStatus;
    }

    public Optional<List<User>> delete(String userName) {
        Optional<List<User>> userOptional = getUser(userName);
        if (userOptional.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(userOptional.get());
            entityManager.getTransaction().commit();
        }
        return userOptional;
    }

    public Optional<List<User>> getUser(String userName) {
        return Optional.ofNullable(entityManager
                .createQuery("select user from User user where user.userName like :userName")
                .setParameter("userName", userName).getResultList());
    }
}
