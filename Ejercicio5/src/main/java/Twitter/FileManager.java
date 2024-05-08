package Twitter;

import java.io.*;
import java.util.*;

public class FileManager {
    public static Map<String, UserAccount> loadUsersFromFile(String filename) {
        Map<String, UserAccount> accounts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    accounts.put(parts[0].trim(), new UserAccount(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read the users file: " + e.getMessage());
        }
        return accounts;
    }
    public static UserAccount loadUserByAlias(String filename, String alias) {
        Map<String, UserAccount> accounts = loadUsersFromFile(filename);
        accounts.put(null, null); // Centinela
        for (String key : accounts.keySet()) {
            if (key != null && key.equals(alias)) {
                return accounts.get(key);
            }
        }
        return null; // Si no se encuentra, devuelve null
    }

    public static void saveUserToFile(String usersFile, String alias, String email) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile, true))) {
            writer.write(alias + "," + email);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to write the user to the file: " + e.getMessage());
        }
    }
}

