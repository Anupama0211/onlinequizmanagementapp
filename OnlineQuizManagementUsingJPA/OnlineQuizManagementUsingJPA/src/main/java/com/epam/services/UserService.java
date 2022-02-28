package com.epam.services;

import com.epam.dao.UserDAO;
import com.epam.entities.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean registerUser(User user) {
        return userDAO.insert(user);
    }

    public boolean validateCredentials(String name, String password, int choice) {
        boolean check = false;
        Optional<List<User>> userOptional = userDAO.getUser(name);
        if (userOptional.isPresent() && !(userOptional.get().isEmpty())) {
            User user = userOptional.get().get(0);
            if (user.getPassword().equals(password)
                    && ((choice == 1 && user.getType().equalsIgnoreCase("Admin"))
                    || (choice == 2 && user.getType().equalsIgnoreCase("Player")))) {
                check = true;
            }
        }
        return check;
    }
}


