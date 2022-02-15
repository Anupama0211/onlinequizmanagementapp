package com.epam.userinterface.loginui;


import java.util.Optional;

public class LoginFactory {
    public Optional<Login> getLogin(int choice) {
       Login login = null;
        if (choice == 1) {
            login = new AdminLogin();
        } else if (choice == 2) {
            login= new PlayerLogin();
        } else if (choice == 3) {
           login = new AdminRegistration();
        } else if (choice == 4) {
            login = new PlayerRegistration();
        }
        return Optional.ofNullable(login);
    }
}
