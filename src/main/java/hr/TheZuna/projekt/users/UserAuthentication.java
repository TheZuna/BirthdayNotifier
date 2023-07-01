package hr.TheZuna.projekt.users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserAuthentication {
    private static final String USERS_FILE_PATH = "src/main/java/hr/TheZuna/projekt/users/users.txt";

    public static Map<String, String> readUsersFromFile() throws IOException {
        Map<String, String> users = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    users.put(username, password);
                }
            }
        }
        return users;
    }
}
