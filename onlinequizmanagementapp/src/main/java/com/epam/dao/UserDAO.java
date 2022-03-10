package com.epam.dao;

import com.epam.entities.User;
import com.epam.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

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

    public User delete(String userName)throws UserNotFoundException {
        User user = getUser(userName);
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();

        return user;
    }

    public User getUser(String userName)throws UserNotFoundException {
        User user;
        try {
            user=(User) entityManager
                    .createQuery("select user from User user where user.userName like :userName")
                    .setParameter("userName", userName).getSingleResult();
        }
        catch (NoResultException e){
            throw new UserNotFoundException("User doesnt exist!! Register!");
        }
        return user;
    }
}
