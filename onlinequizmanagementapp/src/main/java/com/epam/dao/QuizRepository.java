package com.epam.dao;

import com.epam.entities.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
}
