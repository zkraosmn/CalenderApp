package util;

import model.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static void saveEvents(String username, List<Event> events) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/events/" + username + "_events.ser"))) {
            oos.writeObject(events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Event> loadEvents(String username) {
        File file = new File("data/events/" + username + "_events.ser");
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}