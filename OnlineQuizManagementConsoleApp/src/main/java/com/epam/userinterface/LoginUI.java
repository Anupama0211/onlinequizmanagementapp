package com.epam.userinterface;


import com.epam.services.ValidateUser;

import java.util.Scanner;

public class LoginUI {
    public static boolean loginUser(Scanner scanner) {
       boolean loginStatus;
        System.out.println("Enter Username ");
        String username = scanner.nextLine();
        if (ValidateUser.checkIfNamePresent(username)) {
            System.out.println("Enter Password");
            String password = scanner.nextLine();
            if (ValidateUser.checkCredentials(username, password)) {
                System.out.println("Login Successful");
                loginStatus=true;
            }
            else {
                System.out.println("Invalid Password!!!Try Again");
                loginStatus=false;
            }
        }
        else {
            System.out.println("Invalid Username!!!Try Again");
            loginStatus=false;
        }
        return loginStatus;
    }
}
