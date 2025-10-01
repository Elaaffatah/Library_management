import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import java.io.*;
import java.util.*;

class Admin {
    private static Stack<String> adminLogs = new Stack<>();  // Stack to keep track of admin actions
    private static Map<String, String> users = new HashMap<>();  // HashMap for faster lookup

    static {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                users.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public void editUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the User ID to edit:");
        String userId = sc.nextLine();
        if (!users.containsKey(userId)) {
            System.out.println("User ID not found.");
            return;
        }

        System.out.println("Enter the new Password:");
        String newPassword = sc.nextLine();

        users.put(userId, newPassword);  // Update password in the map
        adminLogs.push("Edited User: " + userId);  // Log the action
        updateUserFile();  // Write the updated user list to the file

        System.out.println("User edited successfully!");
    }

    public void addUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the User ID:");
        String userId = sc.nextLine();
        System.out.println("Enter the Password:");
        String password = sc.nextLine();

        if (users.containsKey(userId)) {
            System.out.println("User already exists!");
        } else {
            users.put(userId, password);
            adminLogs.push("Added User: " + userId);
            updateUserFile();
            System.out.println("User added successfully!");
        }
    }

    // Method to delete a user
    public void deleteUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the User ID to delete:");
        String userId = sc.nextLine();
        if (!users.containsKey(userId)) {
            System.out.println("User ID not found.");
            return;
        }

        users.remove(userId);  // Remove the user from the map
        adminLogs.push("Deleted User: " + userId);  // Log the action
        updateUserFile();  // Write the updated user list to the file

        System.out.println("User deleted successfully!");
    }

    // Helper method to write the updated users to the file
    private void updateUserFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating user file: " + e.getMessage());
        }
    }

    // Method to view the admin logs
    public void viewLogs() {
        if (adminLogs.isEmpty()) {
            System.out.println("No admin actions have been logged.");
        } else {
            System.out.println("Admin Logs:");
            for (String log : adminLogs) {
                System.out.println(log);
            }
        }
    }

    // Main method to simulate admin actions
    public static void main(String[] args) {
        Admin admin = new Admin();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Admin Menu:");
            System.out.println("1. Edit User");
            System.out.println("2. Delete User");
            System.out.println("3. View Admin Logs");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    admin.editUser();
                    break;
                case 2:
                    admin.deleteUser();
                    break;
                case 3:
                    admin.viewLogs();
                    break;
                case 4:
                    System.out.println("Exiting Admin menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }
}
