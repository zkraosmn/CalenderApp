package util;

import model.Event;
import java.io.*;
import java.util.*;

public class FileUtil {
    public static final String EVENT_FILE = "events/";

    public static void saveEvent(Event event) {
        String filename = EVENT_FILE + AuthManager.getCurrentUser() + "_events.ser";
        List<Event> events = loadEvents();
        events.add(event);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Event> loadEvents() {
        String filename = EVENT_FILE + AuthManager.getCurrentUser() + "_events.ser";
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Event>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void deleteEvent(String display) {
        List<Event> events = loadEvents();
        events.removeIf(e -> e.toDisplay().equals(display));
        saveAll(events);
    }

    private static void saveAll(List<Event> events) {
        String filename = EVENT_FILE + AuthManager.getCurrentUser() + "_events.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}