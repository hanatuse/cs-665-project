package edu.bu.met.cs665.service;

/**
 * Name: Yingtong Zhou
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/09/2024
 * Description: NotificationService class to send email notifications to
 * violators.
 */

public class NotificationService {
    public NotificationService() {
        // Default constructor
    }

    public void sendEmail(String email, String message) {
        // Simulated email sending logic
        System.out.println("Email sent to " + email + ": " + message);
    }
}
