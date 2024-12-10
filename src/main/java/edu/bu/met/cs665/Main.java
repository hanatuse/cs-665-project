/**
 * Name: Yingtong Zhou
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/09/2024
 * File Name: Main.java
 * Description: Main class to run the program.
 */

package edu.bu.met.cs665;

import edu.bu.met.cs665.model.User;
import edu.bu.met.cs665.monitor.GymMonitor;
import edu.bu.met.cs665.repository.UserRepository;
import edu.bu.met.cs665.service.NotificationService;
import edu.bu.met.cs665.service.VideoAnalyzer;

public class Main {
  public static void main(String[] args) {
    // Initialize components
    UserRepository userRepository = new UserRepository();
    VideoAnalyzer videoAnalyzer = new VideoAnalyzer();
    NotificationService notificationService = new NotificationService();
    GymMonitor gymMonitor = new GymMonitor(userRepository, videoAnalyzer, notificationService);

    // Add users to repository
    userRepository.addUser(new User("BUID123", "user1@example.com", "photo1.jpg"));
    userRepository.addUser(new User("BUID124", "user2@example.com", "photo2.jpg"));

    // Simulate gym entry attempt
    System.out.println("Simulating gym entry...");
    gymMonitor.monitorEntry("BUID123", "snapshot_data");
  }
}
