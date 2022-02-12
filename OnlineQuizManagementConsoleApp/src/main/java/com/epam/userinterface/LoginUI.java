package com.epam.userinterface;



import com.epam.services.ValidateUserService;
import java.util.Scanner;

public class LoginUI {
    private LoginUI() {

    }

    public static boolean loginUser(Scanner scanner) {
        boolean loginStatus;
        System.out.println("Enter Username ");
        String username = scanner.nextLine();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        if (ValidateUserService.checkCredentials(username, password)) {

            System.out.println("Login Successful");
            loginStatus = true;
        } else {
            System.out.println("Invalid Username or Password!!!Try Again");
            loginStatus = false;
        }
        return loginStatus;
    }
}