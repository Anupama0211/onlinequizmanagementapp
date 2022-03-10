package com.epam.services;

import com.epam.dao.UserDAO;
import com.epam.entities.User;
import com.epam.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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

    public boolean validateCredentials(String name, String password, int choice) throws UserNotFoundException {
        boolean check = false;

        User user = userDAO.getUser(name);
            if (user.getPassword().equals(password)
                    && ((choice == 1 && user.getType().equalsIgnoreCase("Admin"))
                    || (choice == 2 && user.getType().equalsIgnoreCase("Player")))) {
                check = true;
            }

        return check;
    }
}


