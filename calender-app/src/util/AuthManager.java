package util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AuthManager {
    private static final String USERS_FILE = "data/users.txt";

    public static boolean register(String username, String password) {
        Map<String, String> users = loadUsers();
        if (users.containsKey(username)) return false;
        users.put(username, password);
        saveUsers(users);
        return true;
    }

    public static boolean login(String username, String password) {
        Map<String, String> users = loadUsers();
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private static Map<String, String> loadUsers() {
        Map<String, String> users = new HashMap<>();
        File file = new File(USERS_FILE);
        if (!file.exists()) return users;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) users.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static void saveUsers(Map<String, String> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}