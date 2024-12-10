package edu.bu.met.cs665.repository;

import java.util.HashMap;
import java.util.Map;

import edu.bu.met.cs665.model.User;

/**
 * Name: Yingtong Zhou
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/09/2024
 * Description: UserRepository class to store and retrieve user information.
 */

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public UserRepository() {
        // Default constructor
    }

    public void addUser(User user) {
        users.put(user.getBuid(), user);
    }

    public User getUserByBuid(String buid) {
        return users.get(buid);
    }
}
