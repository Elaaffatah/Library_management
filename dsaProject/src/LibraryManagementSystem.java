
import java.io.*;
import java.util.*;

class LibraryManagementSystem {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Library Management System! Please enter: \n1 for Existing User \n2 for New User \n3 for Admin:");

        int userType = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        boolean isSignedIn = false;

        User signIn = new SignIn();
        User signUp = new SignUp();
        BooksAvail booksAvail = new BooksAvail();
        Admin admin = new Admin();

        switch (userType) {
            case 1:
                while (!isSignedIn) {
                    isSignedIn = signIn.input();
                }
                booksAvail.bookAvail();
                break;
            case 2:
                signUp.input();
                System.out.println("\nKindly sign in with your credentials");
                while (!isSignedIn) {
                    isSignedIn = signIn.input();
                }
                booksAvail.bookAvail();
                break;
            case 3:
                System.out.println("Please enter Admin Password:");
                int password = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                if (password == 12345) {
                    System.out.println("Welcome Sir, You can now add, delete, or edit users in the Library Management System");
                    System.out.println("Enter: \n1 to Edit a User \n2 to Delete a User\n3 to Add a User");

                    int adminChoice = sc.nextInt();
                    sc.nextLine(); // Consume the newline character

                    switch (adminChoice) {
                        case 1:
                            admin.editUser();
                            break;
                        case 2:
                            admin.deleteUser();
                            break;
                        case 3:
                            admin.addUser();
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                } else {
                    System.out.println("Access Denied");
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}

