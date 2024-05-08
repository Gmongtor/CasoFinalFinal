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

    public static void saveUserToFile(String filename, String alias, String email) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(alias + "," + email);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to save user to file: " + e.getMessage());
        }
    }
}



