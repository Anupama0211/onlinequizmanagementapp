package com.epam.services;

import com.epam.dao.UserDAO;
import com.epam.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserService {
    @Autowired
    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean registerUser(User user) {
        return userDAO.insert(user);
    }

    public boolean validateCredentials(String name, String password, int choice) {
        boolean check = false;
        Optional<User> userOptional = userDAO.getUser(name);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)
                    && ((choice == 1 && user.getType().equalsIgnoreCase("Admin"))
                    || (choice == 2 && user.getType().equalsIgnoreCase("Player")))) {
                check = true;
            }
        }
        return check;
    }
}


