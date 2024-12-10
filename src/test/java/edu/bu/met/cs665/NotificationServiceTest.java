package edu.bu.met.cs665;

import org.junit.jupiter.api.Test;

import edu.bu.met.cs665.service.NotificationService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for NotificationService
 */

class NotificationServiceTest {

    @Test
    void testSendEmail() {
        // Test that an email can be sent without throwing an exception
        NotificationService notificationService = new NotificationService();

        // Simulate sending an email
        String recipient = "user1@example.com";
        String message = "Test notification";

        try {
            notificationService.sendEmail(recipient, message);
        } catch (Exception e) {
            fail("NotificationService threw an exception: " + e.getMessage());
        }
    }
}
