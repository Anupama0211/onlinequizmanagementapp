package com.epam.dao;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class GetManagerTest {

    @Test
    void getEntityMangerTest(){
        assertInstanceOf(EntityManager.class,GetManager.getEntityManger());
    }
}