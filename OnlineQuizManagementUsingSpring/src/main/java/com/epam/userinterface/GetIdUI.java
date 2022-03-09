package com.epam.userinterface;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetIdUI {
    private static final Logger LOGGER = LogManager.getLogger(GetIdUI.class);
    public int getId(String idFor) {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        while (true) {
            try {
                LOGGER.info("Enter the {} ", idFor);
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                LOGGER.info("Enter a valid  ID");
            }
        }
        return id;
    }

}
