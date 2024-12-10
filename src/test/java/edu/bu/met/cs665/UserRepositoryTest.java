package edu.bu.met.cs665;

import org.junit.jupiter.api.Test;

import edu.bu.met.cs665.model.User;
import edu.bu.met.cs665.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserRepository
 */

class UserRepositoryTest {

    @Test
    void testAddAndGetUser() {
        // Test that a user can be added and retrieved by BUID
        UserRepository userRepository = new UserRepository();

        User user = new User("BUID123", "user1@example.com", "photo1.jpg");
        userRepository.addUser(user);

        User retrievedUser = userRepository.getUserByBuid("BUID123");
        assertNotNull(retrievedUser);
        assertEquals("user1@example.com", retrievedUser.getEmail());
        assertEquals("photo1.jpg", retrievedUser.getPhotoPath());
    }

    @Test
    void testGetUserNotFound() {
        // Test that a user is not found when the BUID does not exist
        UserRepository userRepository = new UserRepository();

        User retrievedUser = userRepository.getUserByBuid("BUID999");
        assertNull(retrievedUser);
    }
}
