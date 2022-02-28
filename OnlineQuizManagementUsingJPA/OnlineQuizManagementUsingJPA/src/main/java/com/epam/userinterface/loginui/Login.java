package com.epam.userinterface.loginui;

import com.epam.services.UserService;


import java.util.Scanner;

public interface Login {
    void perform(Scanner scanner, UserService userService, int choice);
}
