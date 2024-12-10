package edu.bu.met.cs665.model;

/**
 * Name: Yingtong Zhou
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/09/2024
 * Description: Violation class to store violation information including snapshot path and violation time.
 */

import java.time.LocalDateTime;

public class Violation {
    private String buid;
    private String snapshotPath;
    private LocalDateTime timestamp;

    public Violation(String buid, String snapshotPath) {
        this.buid = buid;
        this.snapshotPath = snapshotPath;
        this.timestamp = LocalDateTime.now();
    }

    public String getBuid() {
        return buid;
    }

    public String getSnapshotPath() {
        return snapshotPath;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
