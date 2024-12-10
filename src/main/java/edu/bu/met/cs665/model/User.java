package edu.bu.met.cs665.model;

import java.util.Objects;

/**
 * Name: Yingtong Zhou
 * Course: CS-665 Software Designs & Patterns
 * Date: 12/09/2024
 * Description: User class to store user details (BUID, photo, email).
 * 
 */

public class User {
    private String buid;
    private String email;
    private String photoPath;

    public User(String buid, String email, String photoPath) {
        this.buid = buid;
        this.email = email;
        this.photoPath = photoPath;
    }

    public String getBuid() {
        return buid;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(buid, user.buid) &&
                Objects.equals(email, user.email) &&
                Objects.equals(photoPath, user.photoPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buid, email, photoPath);
    }
}
