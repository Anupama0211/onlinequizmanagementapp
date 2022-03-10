package com.epam.dao;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class GetManagerTest {

    @Test
    void getEntityMangerTest(){
        assertInstanceOf(EntityManager.class,GetManager.getEntityManger());
    }
}