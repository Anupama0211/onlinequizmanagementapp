package com.epam.dao;

import com.epam.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findDistinctByUserName(String userName);
}
