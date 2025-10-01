import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

class SignUp implements User {
    @Override
    public boolean input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a new User ID:");
        String userId = sc.nextLine();
        System.out.println("Enter a new Password:");
        String password = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
            bw.write(userId + "," + password);
            bw.newLine();
            System.out.println("User registered successfully!");
            return true;
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

    public void beginSignUp() {
        input();
    }
}
