import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.*;


import java.io.*;
import java.util.*;

class BooksAvail {
    private static Map<String, Integer> books = new LinkedHashMap<>();
    private static LinkedList<String> bookList = new LinkedList<>(); // Stores book names in order

    static {
        loadBooksData();
    }

    public void bookAvail() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Available Books in Library:");
        for (String book : bookList) {
            System.out.println(book + " - Quantity: " + books.get(book));
        }

        System.out.println("\nEnter the name of the book you want to search:");
        String searchBook = sc.nextLine();

        if (bookList.contains(searchBook) && books.get(searchBook) > 0) {
            System.out.println("Book is available. Would you like to issue it? (yes/no)");
            String issue = sc.nextLine();
            if (issue.equalsIgnoreCase("yes")) {

                books.put(searchBook, Integer.valueOf(books.get(searchBook) - 1));

                if (books.get(searchBook) == 0) {
                    books.remove(searchBook);
                    bookList.remove(searchBook);
                }

                updateBooksData();

                System.out.println("The book \"" + searchBook + "\" has been issued to you.");
                System.out.println("\nRemaining Books in the Library:");
                for (String book : bookList) {
                    System.out.println(book + " - Quantity: " + books.get(book));
                }
            }
        } else {
            System.out.println("Book not found or out of stock.");
        }
    }

    private static void loadBooksData() {
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    String bookName = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    books.put(bookName, Integer.valueOf(quantity));
                    bookList.add(bookName); // Maintain insertion order in the linked list
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    private static void updateBooksData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"))) {
            for (String book : bookList) {
                bw.write(book + " - " + books.get(book));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating books: " + e.getMessage());
        }
    }
}
