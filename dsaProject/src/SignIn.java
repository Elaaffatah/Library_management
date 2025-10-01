import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

interface User {
    boolean input();
}

class SignIn implements User {
    private static HashMap<String, String> userCredentials = new HashMap<>();

    static {
        loadUserData();
    }

    @Override
    public boolean input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your User ID:");
        String userId = sc.nextLine();
        System.out.println("Enter your Password:");
        String password = sc.nextLine();

        if (userCredentials.containsKey(userId) && userCredentials.get(userId).equals(password)) {
            System.out.println("Login successful!");
            return true;  // User is logged in successfully
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return false;
        }
    }

    private static void loadUserData() {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                userCredentials.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
    }
    }