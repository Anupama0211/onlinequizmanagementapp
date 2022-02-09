package com.epam.services;

import com.epam.database.UsersDAO;

public class ValidateUser {
    public static boolean checkIfNamePresent(String userName) {
        return UsersDAO.getUsers().containsKey(userName);
    }
    public static boolean checkCredentials(String adminName, String password) {
        return UsersDAO.getUsers().get(adminName).getPassword().equals(password);
    }

}
