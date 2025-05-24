package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Event implements Serializable {
    private String title;
    private String description;
    private LocalDate date;

    public Event(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String toDisplay() {
        return date + ": " + title + " - " + description;
    }

    public LocalDate getDate() { return date; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}

// AuthManager.java
package util;

import java.io.*;
import java.util.*;

public class AuthManager {
    private static final String FILE = "users.txt";
    private static String currentUser = "";

    public static boolean login(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) return true;
            }
        } catch (IOException ignored) {}
        return false;
    }

    public static boolean register(String username, String password) {
        if (exists(username)) return false;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(username + "," + password);
            bw.newLine();
            return true;
        } catch (IOException ignored) {}
        return false;
    }

    public static boolean exists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username + ",")) return true;
            }
        } catch (IOException ignored) {}
        return false;
    }

    public static void setCurrentUser(String user) {
        currentUser = user;
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}