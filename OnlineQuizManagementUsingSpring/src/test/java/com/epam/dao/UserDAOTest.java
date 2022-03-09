package com.epam.dao;


import com.epam.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDAOTest {
    @Mock
    EntityManager entityManagerMock;
    @Mock
    EntityTransaction entityTransactionmock;
    @Mock
    Query query;
    @InjectMocks
    UserDAO userDAO;

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setType("Admin");
        user.setUserId(2);
        user.setUserName("Anupama");
        user.setPassword("1234");

    }

    @Test
    void insert() {
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionmock);
        assertTrue(userDAO.insert(user));
        verify(entityManagerMock).persist(user);
    }

    @Test
    void delete() {
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionmock);
        when(entityManagerMock.createQuery("select user from User user where user.userName like :userName")).thenReturn(query);
        when(query.setParameter("userName", "Anupama")).thenReturn(query);
        when(query.getSingleResult()).thenReturn(user);
        assertEquals(Optional.ofNullable(user), userDAO.delete("Anupama"));
        verify(entityManagerMock).remove(user);
    }

    @Test
    void getUser() {
        when(entityManagerMock.createQuery("select user from User user where user.userName like :userName")).thenReturn(query);
        when(query.setParameter("userName", "Anupama")).thenReturn(query);
        when(query.getSingleResult()).thenReturn(user);
        assertEquals(Optional.ofNullable(user), userDAO.getUser("Anupama"));
    }
}