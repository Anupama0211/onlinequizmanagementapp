package com.epam.services.userservices;

import com.epam.database.UsersDatabase;
import com.epam.entities.User;

import java.util.Map;

public class ValidatePlayerCredentials implements UserService {
    public boolean perform(String name, String password) {
        boolean check = false;

        Map<String, User> users = UsersDatabase.getUsers();
        if (users.containsKey(name) && users.get(name).getType().equals("Player") && users.get(name).getPassword().equals(password)) {
            check = true;
        }
        return check;
    }
}

