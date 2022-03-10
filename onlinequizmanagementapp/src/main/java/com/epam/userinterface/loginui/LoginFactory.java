package com.epam.userinterface.loginui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class LoginFactory {
    @Autowired
    UserLogin userLogin;
    @Autowired
    UserRegistration userRegistration;
    public Optional<Login> getLogin(int choice) {
        Login login = null;
        if (choice == 1 || choice == 2) {
            login =userLogin;
        } else if (choice == 3 || choice == 4) {
            login = userRegistration;
        }
        return Optional.ofNullable(login);
    }
}
