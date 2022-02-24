package com.epam.dao;

import com.epam.entities.Option;
import com.epam.entities.User;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import java.sql.SQLIntegrityConstraintViolationException;
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
        } catch (ConstraintViolationException e) {
            insertStatus = false;
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
        List<User> user = entityManager
                .createQuery("select user from User user where userName=:userName")
                .setParameter("userName", userName).getResultList();
        Optional<User> userOptional = Optional.ofNullable(user.get(0));
        return userOptional;
    }

}
