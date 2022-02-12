package com.epam.database;

import com.epam.entities.User;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class UsersDatabase {
    static Map<String, User> users = new HashMap<>();

    static {
        users.put("Anupama", new User("Admin", "Anupama ", "abcdef"));
        users.put("Anuj", new User("Admin", "Anuj", "1234563"));
        users.put("Anurag", new User("Admin", "Anurag", "abcdef"));
    }
    private UsersDatabase() {

    }


    public static boolean checkPassword(String name, String password) {
        return users.get(name).getPassword().equals(password);
    }


    public static void addUser(User newUser) {
        users.put(newUser.getName(), newUser);
    }

    public static void deleteUser(String name) {
        users.remove(name);
    }



    public static List<User> getUsers() {
        return users.values()
                .stream()
                .map(user -> new User(user.getType(), user.getName(), ""))
                .collect(Collectors.toList());
    }
}
