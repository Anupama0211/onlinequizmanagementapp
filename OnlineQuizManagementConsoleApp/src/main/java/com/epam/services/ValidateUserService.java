package com.epam.services;


import com.epam.database.UsersDatabase;

public class ValidateUserService {

    private ValidateUserService() {
    }

    public static boolean checkCredentials(String name, String password) {
        return UsersDatabase.checkPassword(name, password);
    }

}
