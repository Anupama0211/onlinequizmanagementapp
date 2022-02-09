package com.epam.database;

import com.epam.entities.User;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class UsersDAO {
    static Map<String, User> users = new HashMap<>();

    static {
        users.put("Anupama", new User("Admin", "Anupama ", "02111999"));
        users.put("Anuj", new User("Admin", "Anuj", "1234563"));
        users.put("Anurag", new User("Admin", "Anurag", "abcdef"));
    }


    public static void addUser(User newUser) {
        users.put(newUser.getName(), newUser);
    }

    public static void deleteUser(String name) {
        users.remove(name);
    }

    public  static Map<String,User> getUsers(){
        return users;
    }

    public static List<User> getAdmins() {
        return users.values()
                .stream()
                .filter(user -> user.getType().equalsIgnoreCase("Admin"))
                .collect(Collectors.toList());
    }

}
