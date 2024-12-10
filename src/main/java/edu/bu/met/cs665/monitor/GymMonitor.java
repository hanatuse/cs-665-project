package edu.bu.met.cs665.monitor;

import java.util.ArrayList;
import java.util.List;

import edu.bu.met.cs665.model.User;
import edu.bu.met.cs665.model.Violation;
import edu.bu.met.cs665.repository.UserRepository;
import edu.bu.met.cs665.service.NotificationService;
import edu.bu.met.cs665.service.VideoAnalyzer;

/**
 * Name: Yingtong Zhou
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/09/2024
 * Description: GymMonitor class to monitor gym entry and log violations.
 */

public class GymMonitor {
    private UserRepository userRepository;
    private VideoAnalyzer videoAnalyzer;
    private NotificationService notificationService;
    private List<Violation> violations;

    public GymMonitor(UserRepository userRepository, VideoAnalyzer videoAnalyzer,
            NotificationService notificationService) {
        this.userRepository = userRepository;
        this.videoAnalyzer = videoAnalyzer;
        this.notificationService = notificationService;
        this.violations = new ArrayList<>();
    }

    public void monitorEntry(String scannedBuid, String snapshotPath) {
        User registeredUser = userRepository.getUserByBuid(scannedBuid);
        User matchedUser = videoAnalyzer.identifyPerson(snapshotPath);

        if (registeredUser == null || !registeredUser.getPhotoPath().equals(matchedUser.getPhotoPath())) {
            logViolation(scannedBuid, snapshotPath);
        } else {
            System.out.println("Access granted for BUID: " + scannedBuid);
        }
    }

    private void logViolation(String buid, String snapshotPath) {
        Violation violation = new Violation(buid, snapshotPath);
        violations.add(violation);

        User violator = userRepository.getUserByBuid(buid);
        if (violator != null) {
            notificationService.sendEmail(violator.getEmail(),
                    "Violation detected: Unauthorized use of BUID " + buid + ". Penalty applied.");
        }
    }

    public List<Violation> getViolations() {
        return violations;
    }
}
