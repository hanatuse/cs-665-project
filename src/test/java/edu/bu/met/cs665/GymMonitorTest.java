package edu.bu.met.cs665;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.bu.met.cs665.model.User;
import edu.bu.met.cs665.monitor.GymMonitor;
import edu.bu.met.cs665.repository.UserRepository;
import edu.bu.met.cs665.service.NotificationService;
import edu.bu.met.cs665.service.VideoAnalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for GymMonitor
 */

class GymMonitorTest {
    private UserRepository userRepository;
    private MockVideoAnalyzer videoAnalyzer;
    private MockNotificationService notificationService;
    private GymMonitor gymMonitor;

    @BeforeEach
    void setup() {
        userRepository = new UserRepository();
        videoAnalyzer = new MockVideoAnalyzer(); // Mock VideoAnalyzer
        notificationService = new MockNotificationService(); // Mock NotificationService
        gymMonitor = new GymMonitor(userRepository, videoAnalyzer, notificationService);

        // Add users to the repository
        userRepository.addUser(new User("BUID123", "user1@example.com",
                "photo1.jpg"));
    }

    @Test
    void testValidEntry() {
        // Mock VideoAnalyzer to return the correct user
        videoAnalyzer.setIdentifiedUser(new User("BUID123", "user1@example.com", "photo1.jpg"));

        // Simulate a valid gym entry
        gymMonitor.monitorEntry("BUID123", "snapshot_path");

        // Assert no violations were logged
        assertEquals(0, gymMonitor.getViolations().size());

        // Verify no notification was sent
        assertEquals(0, notificationService.getSentEmails().size());
    }

    @Test
    void testViolationLogged() {
        // Mock VideoAnalyzer to return a different user
        videoAnalyzer.setIdentifiedUser(new User("BUID999", "user2@example.com", "photo2.jpg"));

        // Simulate an invalid gym entry
        gymMonitor.monitorEntry("BUID123", "snapshot_path");

        // Assert that a violation was logged
        assertEquals(1, gymMonitor.getViolations().size());

        // Verify that a notification was sent
        assertEquals(1, notificationService.getSentEmails().size());
        assertEquals("user1@example.com", notificationService.getSentEmails().get(0).getRecipient());
        assertEquals("Violation detected: Unauthorized use of BUID BUID123. Penalty applied.",
                notificationService.getSentEmails().get(0).getMessage());
    }

    private static class MockVideoAnalyzer extends VideoAnalyzer {
        private User identifiedUser;

        void setIdentifiedUser(User user) {
            this.identifiedUser = user;
        }

        @Override
        public User identifyPerson(String snapshotPath) {
            return identifiedUser;
        }
    }

    private static class MockNotificationService extends NotificationService {
        private final List<SentEmail> sentEmails = new ArrayList<>();

        @Override
        public void sendEmail(String recipient, String message) {
            sentEmails.add(new SentEmail(recipient, message));
        }

        public List<SentEmail> getSentEmails() {
            return sentEmails;
        }

        // Helper class to store sent email data
        private static class SentEmail {
            private final String recipient;
            private final String message;

            public SentEmail(String recipient, String message) {
                this.recipient = recipient;
                this.message = message;
            }

            public String getRecipient() {
                return recipient;
            }

            public String getMessage() {
                return message;
            }
        }
    }

}
