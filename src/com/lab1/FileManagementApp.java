package com.lab1;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FileManagementApp {
    private static final String ROOT_DIRECTORY = "C:\\Users\\Bhavesh15223\\Desktop\\Test";

    public static void main(String[] args) {
        displayWelcomeScreen();
    }

    public static void displayWelcomeScreen() {
        System.out.println("Welcome to File Manager App");
        System.out.println("Developed by Bhavesh Kolluri");

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Display current file names in ascending order");
            System.out.println("2. User Interface Options");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1:
                    displayFileNames();
                    break;
                case 2:
                    displayUIOptions(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    public static void displayFileNames() {
        File rootDirectory = new File(ROOT_DIRECTORY);
        if (!rootDirectory.exists() || !rootDirectory.isDirectory()) {
            System.out.println("Root directory does not exist or is not a directory.");
            return;
        }

        File[] files = rootDirectory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in the root directory.");
            return;
        }

        List<String> fileNames = new ArrayList<>();
        for (File file : files) {
            fileNames.add(file.getName());
        }

        Collections.sort(fileNames);

        System.out.println("Current file names in ascending order:");
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
    }

    public static void displayUIOptions(Scanner scanner) {
        int choice;

        do {
            System.out.println("\nUser Interface Options:");
            System.out.println("1. Add a file to the existing directory list");
            System.out.println("2. Delete a user specified file from the existing directory list");
            System.out.println("3. Search a user specified file from the main directory");
            System.out.println("4. Navigate back to the main context");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1:
                    addFile(scanner);
                    break;
                case 2:
                    deleteFile(scanner);
                    break;
                case 3:
                    searchFile(scanner);
                    break;
                case 4:
                    return; 
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (true);
    }

    public static void addFile(Scanner scanner) {
        System.out.println("Enter the file name to add:");
        String fileName = scanner.nextLine();

        File file = new File(ROOT_DIRECTORY + File.separator + fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File added successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while adding the file: " + e.getMessage());
        }
    }

    public static void deleteFile(Scanner scanner) {
        System.out.println("Enter the file name to delete:");
        String fileName = scanner.nextLine();

        File file = new File(ROOT_DIRECTORY + File.separator + fileName);
        try {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("File does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while deleting the file: " + e.getMessage());
        }
    }

    public static void searchFile(Scanner scanner) {
        System.out.println("Enter the file name to search:");
        String fileName = scanner.nextLine();

        File rootDirectory = new File(ROOT_DIRECTORY);
        if (!rootDirectory.exists() || !rootDirectory.isDirectory()) {
            System.out.println("Root directory does not exist or is not a directory.");
            return;
        }

        File[] files = rootDirectory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in the root directory.");
            return;
        }

        for (File file : files) {
            if (file.getName().equalsIgnoreCase(fileName)) {
                System.out.println("File found: " + file.getAbsolutePath());
                return;
            }
        }

        System.out.println("File not found.");
    }
}
