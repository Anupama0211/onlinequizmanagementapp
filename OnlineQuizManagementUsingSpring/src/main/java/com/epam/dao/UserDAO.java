package com.epam.dao;

import com.epam.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.Optional;
@Component
public class UserDAO {

    @Autowired
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
        User user;
        try {
            user=(User) entityManager
                    .createQuery("select user from User user where user.userName like :userName")
                    .setParameter("userName", userName).getSingleResult();
        }
        catch (NoResultException e){
            user=null;
        }
        return Optional.ofNullable(user);
    }
}
