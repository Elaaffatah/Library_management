package library;
import java.util.*;


class user {
    static LinkedList<String> users = new LinkedList<>(); // LinkedList for user management

    public boolean input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = sc.nextLine();

        if (users.contains(username)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("User not found! Please sign up.");
            return false;
        }
    }
}

