package com.zipcodewilmington.centrallibrary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class MainApplication {

        private static Library currentLibrary = null;
        private static Librarian currentLibrarian = null;
        private static LibraryMember currentLibraryMember = null;

        public static void main(String[] args) {
                Library library1 = new Library("Central Library",
                                new Address("123 Main St", "Wilmington", "Delaware", "19801"));
                LibraryMember AliceJohnson = new LibraryMember("Alice Johnson", 25, "alice@email.com", "302-456-7890",
                                001,
                                LocalDate.now().toString(),
                                new Address("12 Jump St", "New Castle", "Delaware", "19720"));
                LibraryMember BobWilson = new LibraryMember("Bob Wilson", 35, "bob@email.com", "302-456-7890", 002,
                                LocalDate.now().toString(),
                                new Address("654 Maple St", "Media", "Pennsylvania", "19742"));
                Librarian librarian = new Librarian("Hypatia", 67, "Hypatia58@library.com", "302-765-4321", "L001",
                                "Front Desk", 45000, null);

                library1.addLibrarian(librarian);
                library1.addMember(AliceJohnson);
                library1.addMember(BobWilson);

                long autoIncrementedId = 0;

                try {
                        JsonArray jArray = JsonParser.parseReader(new FileReader(
                                        "src/main/java/com/zipcodewilmington/centrallibrary/Data/music_json.json"))
                                        .getAsJsonArray();
                        for (JsonElement element : jArray) {
                                JsonObject obj = element.getAsJsonObject();
                                Music music = new Music(autoIncrementedId, obj.get("track_name").getAsString(),
                                                library1, obj.get("artist_name").getAsString(),
                                                obj.get("release_date").getAsString(), obj.get("genre").getAsString());
                                library1.addItem(music);
                                autoIncrementedId++;
                        }
                } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        JsonArray jArray = JsonParser.parseReader(new FileReader(
                                        "src/main/java/com/zipcodewilmington/centrallibrary/Data/final_movies_data.json"))
                                        .getAsJsonArray();
                        for (JsonElement jsonElement : jArray) {
                                JsonObject obj = jsonElement.getAsJsonObject();

                                // Safely extract genres
                                String genres = "";
                                if (obj.has("genres") && obj.get("genres").isJsonArray()) {
                                        for (JsonElement gElement : obj.get("genres").getAsJsonArray()) {
                                                if (!gElement.isJsonNull()) {
                                                        genres += gElement.getAsString() + " ";
                                                }
                                        }
                                }

                                // Safely extract rating
                                JsonElement el = obj.get("rating");
                                String value = "no rating";
                                if (el != null && !el.isJsonNull()) {
                                        value = String.valueOf(el.getAsDouble());
                                }

                                // Safely extract runtime
                                JsonElement el2 = obj.get("runtime");
                                int runtime = -1;
                                if (el2 != null && !el2.isJsonNull()) {
                                        runtime = (int) el2.getAsDouble();
                                }

                                // Safely extract title
                                String title = "Untitled";
                                if (obj.has("title") && !obj.get("title").isJsonNull()) {
                                        title = obj.get("title").getAsString();
                                }

                                // Safely extract director
                                String director = "Unknown";
                                if (obj.has("director") && !obj.get("director").isJsonNull()) {
                                        director = obj.get("director").getAsString();
                                }

                                // Create and add DVD object
                                DVD dvd = new DVD(autoIncrementedId, title, library1, director, runtime, value, genres);
                                autoIncrementedId++;
                                library1.addItem(dvd);
                        }

                } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        JsonArray jArray = JsonParser.parseReader(new FileReader(
                                        "src/main/java/com/zipcodewilmington/centrallibrary/Data/book_json.json"))
                                        .getAsJsonArray();
                        for (JsonElement jsonElement : jArray) {
                                JsonObject obj = jsonElement.getAsJsonObject();
                                String genres = "";
                                String authors = "";
                                for (JsonElement gElement : obj.get("Genres").getAsJsonArray()) {
                                        genres += gElement.getAsString() + " ";
                                }
                                for (JsonElement gElement : obj.get("Authors").getAsJsonArray()) {
                                        authors += gElement.getAsString() + " ";
                                }
                                Book book = new Book(autoIncrementedId, obj.get("Title").getAsString(), library1, "hi",
                                                obj.get("ISBN").getAsString(), obj.get("Pages").getAsInt(), authors);
                                autoIncrementedId++;
                                library1.addItem(book);
                        }
                } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                Scanner scanner = new Scanner(System.in);
                List<Library> libraries = new ArrayList<>();
                libraries.add(library1);

                // Call setStates once at startup to select library only
                selectLibraryOnly(scanner, libraries);

                int choice = -1;
                do {
                        flushScreen();
                        displayMainTitle();

                        System.out.println("┌─────────────────────────────────────────────────────────────┐");
                        System.out.println("│                        MAIN MENU                            │");
                        System.out.println("├─────────────────────────────────────────────────────────────┤");
                        System.out.println("│  0. Exit System                                             │");
                        System.out.println("│  1. Librarian Portal                                        │");
                        System.out.println("│  2. Library Member Portal                                   │");
                        System.out.println("│  3. Change Library                                          │");
                        System.out.println("└─────────────────────────────────────────────────────────────┘");
                        System.out.println();
                        System.out.print("Select Option (0-3): ");

                        // Input validation
                        while (!scanner.hasNextInt()) {
                                System.out.println("\nInvalid input. Please enter a number between 0 and 3.");
                                System.out.print("Select Option (0-3): ");
                                scanner.next();
                        }
                        choice = scanner.nextInt();
                        while (choice < 0 || choice > 3) {
                                System.out.println("\nInvalid choice. Please enter a number between 0 and 3.");
                                System.out.print("Select Option (0-3): ");
                                while (!scanner.hasNextInt()) {
                                        System.out.println("\nInvalid input. Please enter a number between 0 and 3.");
                                        System.out.print("Select Option (0-3): ");
                                        scanner.next();
                                }
                                choice = scanner.nextInt();
                        }
                        scanner.nextLine();

                        switch (choice) {
                                case 1:
                                        // Select librarian before accessing librarian portal
                                        if (currentLibrarian == null) {
                                                selectLibrarianOnly(scanner);
                                        }
                                        librarianOptions(scanner);
                                        break;
                                case 2:
                                        // Select member before accessing member portal
                                        if (currentLibraryMember == null) {
                                                selectMemberOnly(scanner);
                                        }
                                        libraryMemberOptions(scanner);
                                        break;
                                case 3:
                                // Change Library - Reset and select new library
                                        flushScreen();
                                        System.out.println("╔══════════════════════════════════════════════════════════════╗");
                                        System.out.println("║                    CHANGE LIBRARY                           ║");
                                        System.out.println("║              Select a Different Library Branch              ║");
                                        System.out.println("╚══════════════════════════════════════════════════════════════╝");
                                        System.out.println();
    
                                        // Reset current selections since we're changing libraries
                                        currentLibrarian = null;
                                        currentLibraryMember = null;
    
                                        // Go directly to library selection
                                        selectLibraryOnly(scanner, libraries);
                                        break;
                                case 0:
                                        flushScreen();
                                        System.out.println(
                                                        "╔══════════════════════════════════════════════════════════════╗");
                                        System.out.println(
                                                        "║                    Thank you for using                       ║");
                                        System.out.println(
                                                        "║                  CENTRAL LIBRARY SYSTEM                      ║");
                                        System.out.println(
                                                        "║                     See You Next Time!                       ║");
                                        System.out.println(
                                                        "╚══════════════════════════════════════════════════════════════╝");
                                        break;
                                default:
                                        break;
                        }

                } while (choice != 0);
                scanner.close();
        }

        private static void flushScreen() {
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }

        private static void displayMainTitle() {
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                    CENTRAL LIBRARY SYSTEM                    ║");
                System.out.println("║                     Management Portal                        ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();
        }

        private static void displayLibrarianTitle() {
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                      LIBRARIAN PORTAL                        ║");
                System.out.println("║             " + currentLibrary.getLibraryName()
                                + " - Admin Functions                ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();
        }

        private static void displayMemberTitle() {
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                     MEMBER PORTAL                            ║");
                System.out.println("║                   Welcome " + currentLibraryMember.getName()
                                + "                      ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();
        }

        private static void displaySectionDivider(String title) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("                    " + title.toUpperCase());
                System.out.println("=".repeat(60) + "\n");
        }

        private static void waitForEnter(Scanner scanner, String message) {
                System.out.println("\n" + "-".repeat(60));
                System.out.println(message);
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
        }

        private static void librarianOptions(Scanner scanner) {
                int choice = -1;
                do {
                        flushScreen();
                        displayLibrarianTitle();

                        System.out.println("┌─────────────────────────────────────────────────────────────┐");
                        System.out.println("│                    LIBRARIAN FUNCTIONS                      │");
                        System.out.println("├─────────────────────────────────────────────────────────────┤");
                        System.out.println("│  0. Return to Main Menu                                     │");
                        System.out.println("│  1. View All Items                                          │");
                        System.out.println("│  2. Search for Item                                         │");
                        System.out.println("│  3. Add Item to Library                                     │");
                        System.out.println("│  4. Remove Item from Library                                │");
                        System.out.println("│  5. Generate Late Fee Report                                │");
                        System.out.println("└─────────────────────────────────────────────────────────────┘");
                        System.out.println();
                        System.out.print("Select Option (0-5): ");

                        // Input validation
                        while (!scanner.hasNextInt()) {
                                System.out.println("\nInvalid input. Please enter a number between 0 and 5.");
                                System.out.print("Select Option (0-5): ");
                                scanner.next();
                        }
                        choice = scanner.nextInt();
                        while (choice < 0 || choice > 5) {
                                System.out.println("\nInvalid choice. Please enter a number between 0 and 5.");
                                System.out.print("Select Option (0-5): ");
                                while (!scanner.hasNextInt()) {
                                        System.out.println("\nInvalid input. Please enter a number between 0 and 5.");
                                        System.out.print("Select Option (0-5): ");
                                        scanner.next();
                                }
                                choice = scanner.nextInt();
                        }
                        scanner.nextLine();

                        switch (choice) {
                                case 1:
                                        // Display all items in the library
                                        flushScreen();
                                        currentLibrary.displayAllItems();
                                        waitForEnter(scanner, "Inventory display complete.");
                                        break;
                                case 2:
                                        // Search for an item in the library
                                        flushScreen();
                                        displaySectionDivider("Search Library Items");
                                        System.out.print("Enter Keyword: ");
                                        String keyword = scanner.nextLine();
                                        System.out.println(
                                                        "\n" + currentLibrary.getLibraryName() + "'s Search Results:");
                                        System.out.println("-".repeat(55));
                                        List<LibraryItem> temp = currentLibrary.search(keyword);
                                        if (temp.isEmpty()) {
                                                System.out.println("No items found matching: " + keyword);
                                        } else {
                                                for (LibraryItem item : temp) {
                                                        System.out.println(
                                                                        item.getItemType() + ":\t" + item.getTitle());
                                                }
                                        }
                                        waitForEnter(scanner, "Search complete.");
                                        break;
                                case 3:
                                        // Add an item to the library
                                        addItemToLibrary(scanner);
                                        break;
                                case 4:
                                        // Remove an item from the library
                                        removeItemfromLibrary(scanner);
                                        break;
                                case 5:
                                        // Generate a late fee report for the library
                                        flushScreen();
                                        displaySectionDivider("Late Fee Report");
                                        System.out.println(currentLibrary.getLibraryName() + "'s Late Fee Report:");
                                        System.out.println("-".repeat(50));
                                        currentLibrary.generateReportItems();
                                        waitForEnter(scanner, "Report generation complete.");
                                        break;
                                default:
                                        // Exit to main menu
                                        break;
                        }
                } while (choice != 0);
        }

        private static void libraryMemberOptions(Scanner scanner) {
                int choice = -1;
                do {
                        flushScreen();
                        displayMemberTitle();

                        // Display member info
                        System.out.println("Account Information:");
                        System.out.println("   Member: " + currentLibraryMember.getName());
                        System.out.println("   Outstanding Fees: $"
                                        + String.format("%.2f", currentLibraryMember.getOutstandingFees()));
                        System.out.println("   Items Borrowed: " + currentLibraryMember.getBorrowedItems().size());
                        System.out.println("   Items Reserved: " + currentLibraryMember.getReservedItems().size());
                        System.out.println();

                        System.out.println("┌─────────────────────────────────────────────────────────────┐");
                        System.out.println("│                    MEMBER FUNCTIONS                         │");
                        System.out.println("├─────────────────────────────────────────────────────────────┤");
                        System.out.println("│  0. Return to Main Menu                                     │");
                        System.out.println("│  1. View Borrowed Items                                     │");
                        System.out.println("│  2. Search for Item                                         │");
                        System.out.println("│  3. Check Out Item                                          │");
                        System.out.println("│  4. Return Item                                             │");
                        System.out.println("│  5. Reserve Item                                            │");
                        System.out.println("│  6. Cancel Reservation                                      │");
                        System.out.println("│  7. Pay Fees                                                │");
                        System.out.println("└─────────────────────────────────────────────────────────────┘");
                        System.out.println();
                        System.out.print("Select Option (0-7): ");

                        // Input validation
                        while (!scanner.hasNextInt()) {
                                System.out.println("\nInvalid input. Please enter a number between 0 and 7.");
                                System.out.print("Select Option (0-7): ");
                                scanner.next();
                        }
                        choice = scanner.nextInt();
                        while (choice < 0 || choice > 7) {
                                System.out.println("\nInvalid choice. Please enter a number between 0 and 7.");
                                System.out.print("Select Option (0-7): ");
                                while (!scanner.hasNextInt()) {
                                        System.out.println("\nInvalid input. Please enter a number between 0 and 7.");
                                        System.out.print("Select Option (0-7): ");
                                        scanner.next();
                                }
                                choice = scanner.nextInt();
                        }
                        scanner.nextLine();

                        switch (choice) {
                                case 1:
                                        // View borrowed items
                                        flushScreen();
                                        displaySectionDivider("Your Borrowed Items");
                                        viewBorrowedItems();
                                        waitForEnter(scanner, "Borrowed items review complete.");
                                        break;
                                case 2:
                                        // Search for an item in the library
                                        flushScreen();
                                        displaySectionDivider("Search Library Items");
                                        System.out.print("Enter Keyword: ");
                                        String keyword = scanner.nextLine();
                                        System.out.println(
                                                        "\n" + currentLibrary.getLibraryName() + "'s Search Results:");
                                        System.out.println("-".repeat(50));
                                        List<LibraryItem> temp = currentLibrary.search(keyword);
                                        if (temp.isEmpty()) {
                                                System.out.println("No items found matching: " + keyword);
                                        } else {
                                                for (LibraryItem item : temp) {
                                                        System.out.println(
                                                                        item.getItemType() + ":\t" + item.getTitle());
                                                }
                                        }
                                        waitForEnter(scanner, "Search complete.");
                                        break;
                                case 3:
                                        // Check out an item from the library
                                        flushScreen();
                                        displaySectionDivider("Check Out Item");
                                        checkout(scanner);
                                        break;
                                case 4:
                                        // Return an item to the library
                                        flushScreen();
                                        displaySectionDivider("Return Item");
                                        checkInItemfromMember(scanner);
                                        break;
                                case 5:
                                        // Reserve an item from the library
                                        flushScreen();
                                        displaySectionDivider("Reserve Item");
                                        reserve(scanner);
                                        break;
                                case 6:
                                        // Cancel a reservation
                                        flushScreen();
                                        displaySectionDivider("Cancel Reservation");
                                        temp = currentLibraryMember.getReservedItems();
                                        if (temp.isEmpty()) {
                                                System.out.println("You have no items reserved.");
                                                waitForEnter(scanner, "No reservations to cancel.");
                                                break;
                                        }
                                        // Display reserved items
                                        System.out.println("Your Reserved Items:");
                                        System.out.println("-".repeat(30));
                                        int i = 1;
                                        for (LibraryItem item : temp) {
                                                System.out.println("  " + i + ". " + item.getTitle());
                                                i++;
                                        }
                                        System.out.println();

                                        // Input validation for item selection
                                        System.out.print("Enter item number to cancel (1-" + temp.size() + "): ");
                                        while (!scanner.hasNextInt()) {
                                                System.out.println("\nInvalid input. Please enter a valid number.");
                                                System.out.print("Enter item number to cancel (1-" + temp.size()
                                                                + "): ");
                                                scanner.next();
                                        }
                                        // Validate item number
                                        int x = scanner.nextInt();
                                        while (x < 1 || x > temp.size()) {
                                                System.out.println(
                                                                "\nInvalid choice. Please enter a number between 1 and "
                                                                                + temp.size());
                                                System.out.print("Enter item number to cancel (1-" + temp.size()
                                                                + "): ");
                                                while (!scanner.hasNextInt()) {
                                                        System.out.println(
                                                                        "\nInvalid input. Please enter a valid number.");
                                                        System.out.print("Enter item number to cancel (1-" + temp.size()
                                                                        + "): ");
                                                        scanner.next();
                                                }
                                                x = scanner.nextInt();
                                        }
                                        // Remove reservation
                                        scanner.nextLine();
                                        currentLibraryMember.removeReservedItem(temp.get(x - 1));
                                        System.out.println("\nReservation cancelled successfully!");
                                        waitForEnter(scanner, "Reservation cancellation complete.");
                                        break;
                                case 7:
                                        // Pay outstanding fees
                                        flushScreen();
                                        displaySectionDivider("Pay Outstanding Fees");
                                        System.out.println("Payment Portal");
                                        System.out.println("-".repeat(20));
                                        System.out.println("Member: " + currentLibraryMember.getName());
                                        System.out.println("Amount Owed: $" + String.format("%.2f",
                                                        currentLibraryMember.getOutstandingFees()));
                                        System.out.println();
                                        System.out.print("How much would you like to pay: $");

                                        while (!scanner.hasNextDouble()) {
                                                System.out.println("\nInvalid input. Please enter a valid amount.");
                                                System.out.print("How much would you like to pay: $");
                                                scanner.next();
                                        }
                                        Double amount = scanner.nextDouble();
                                        while (amount < 0) {
                                                System.out.println(
                                                                "\nAmount cannot be negative. Please enter a positive amount.");
                                                System.out.print("How much would you like to pay: $");
                                                while (!scanner.hasNextDouble()) {
                                                        System.out.println(
                                                                        "\nInvalid input. Please enter a valid amount.");
                                                        System.out.print("How much would you like to pay: $");
                                                        scanner.next();
                                                }
                                                amount = scanner.nextDouble();
                                        }
                                        scanner.nextLine();
                                        currentLibraryMember.payFees(amount);
                                        System.out.println("\nPayment of $" + String.format("%.2f", amount)
                                                        + " processed successfully!");
                                        System.out.println("Remaining balance: $" + String.format("%.2f",
                                                        currentLibraryMember.getOutstandingFees()));
                                        waitForEnter(scanner, "Payment processing complete.");
                                        break;
                                default:
                                        // Exit to main menu
                                        break;
                        }
                } while (choice != 0);
        }

        private static void checkInItemfromMember(Scanner scanner) {
                int i = 1;
                List<LibraryItem> temp = currentLibraryMember.getBorrowedItems();
                for (LibraryItem item : temp) {
                        System.out.println(i + ". " + item); //
                        i++;
                }
                if (temp.size() != 0) {
                        System.out.print("Please enter number of item you would like to check in: ");

                        // Input validated here
                        while (!scanner.hasNextInt()) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                System.out.print("Please enter number of item you would like to check in: ");
                                scanner.next();
                        }
                        int choice = scanner.nextInt();
                        while (choice < 1 || choice > temp.size()) {
                                System.out.println(
                                                "Invalid choice. Please enter a number between 1 and " + temp.size());
                                System.out.print("Please enter number of item you would like to check in: ");
                                while (!scanner.hasNextInt()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        System.out.print("Please enter number of item you would like to check in: ");
                                        scanner.next();
                                }
                                choice = scanner.nextInt();
                        }
                        scanner.nextLine();

                        System.out.print("Please enter number of days late: ");

                        // Input validated here
                        while (!scanner.hasNextInt()) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                System.out.print("Please enter number of days late: ");
                                scanner.next();
                        }
                        int late = scanner.nextInt();
                        while (late < 0) {
                                System.out.println(
                                                "Days late cannot be negative. Please enter 0 or a positive number.");
                                System.out.print("Please enter number of days late: ");
                                while (!scanner.hasNextInt()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        System.out.print("Please enter number of days late: ");
                                        scanner.next();
                                }
                                late = scanner.nextInt();
                        }
                        scanner.nextLine();
                        currentLibraryMember.returnItem(temp.get(choice - 1), late);
                }
        }

        private static List<LibraryItem> displayItemsChoice(Scanner scanner) {
                int i = 1;
                System.out.print("Enter Keyword: ");
                String keyword = scanner.nextLine();
                System.out.println(currentLibrary.getLibraryName() + "'s Results:");
                System.out.println("-".repeat(80));

                List<LibraryItem> temp = currentLibrary.search(keyword);

                if (temp.isEmpty()) {
                        System.out.println("No items found matching: " + keyword);
                        return temp;
                }

                System.out.println("┌────┬──────────────┬────────────────────────────────────────┬─────────────┐");
                System.out.println("│ #  │ Type         │ Title                                  │ Status      │");
                System.out.println("├────┼──────────────┼────────────────────────────────────────┼─────────────┤");

                for (LibraryItem item : temp) {
                        String status = item.isAvailable() ? "Available" : "Checked Out";
                        String truncatedTitle = truncateTitle(item.getTitle(), 38); // Match the new width
                        String type = item.getItemType();

                        System.out.printf("│ %-2d │ %-12s │ %-38s │ %-11s │%n",
                                        i, type, truncatedTitle, status);
                        i++;
                }

                System.out.println("└────┴──────────────┴────────────────────────────────────────┴─────────────┘");
                System.out.println();

                return temp;
        }

        private static void reserve(Scanner scanner) {
                List<LibraryItem> temp = displayItemsChoice(scanner);
                if (temp.size() != 0) {
                        System.out.println("Please enter number of item you would like to checkout:");

                        // Input validated here
                        while (!scanner.hasNextInt()) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                System.out.print("Please enter number of item you would like to checkout:");
                                scanner.next();
                        }
                        int choice = scanner.nextInt();
                        while (choice < 1 || choice > temp.size()) {
                                System.out.println(
                                                "Invalid choice. Please enter a number between 1 and " + temp.size());
                                System.out.print("Please enter number of item you would like to checkout:");
                                while (!scanner.hasNextInt()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        System.out.print("Please enter number of item you would like to checkout:");
                                        scanner.next();
                                }
                                choice = scanner.nextInt();
                        }
                        scanner.nextLine();
                        currentLibraryMember.reserveItem(temp.get(choice - 1));
                }
        }

        private static void checkout(Scanner scanner) {
                List<LibraryItem> temp = displayItemsChoice(scanner);
                if (temp.size() != 0) {
                        System.out.println("Please enter number of item you would like to checkout:");

                        // Input validated here
                        while (!scanner.hasNextInt()) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                System.out.print("Please enter number of item you would like to checkout:");
                                scanner.next();
                        }
                        int choice = scanner.nextInt();
                        while (choice < 1 || choice > temp.size()) {
                                System.out.println(
                                                "Invalid choice. Please enter a number between 1 and " + temp.size());
                                System.out.print("Please enter number of item you would like to checkout:");
                                while (!scanner.hasNextInt()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        System.out.print("Please enter number of item you would like to checkout:");
                                        scanner.next();
                                }
                                choice = scanner.nextInt();
                        }
                        scanner.nextLine();
                        currentLibraryMember.borrowItem(temp.get(choice - 1));
                }
        }

        private static void removeItemfromLibrary(Scanner scanner) {
                List<LibraryItem> temp = displayItemsChoice(scanner);
                if (temp.size() != 0) {
                        System.out.println("Please enter number of item you would like to remove:");

                        // Input validated here
                        while (!scanner.hasNextInt()) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                System.out.print("Please enter number of item you would like to remove:");
                                scanner.next();
                        }
                        int choice = scanner.nextInt();
                        while (choice < 1 || choice > temp.size()) {
                                System.out.println(
                                                "Invalid choice. Please enter a number between 1 and " + temp.size());
                                System.out.print("Please enter number of item you would like to remove:");
                                while (!scanner.hasNextInt()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        System.out.print("Please enter number of item you would like to remove:");
                                        scanner.next();
                                }
                                choice = scanner.nextInt();
                        }
                        scanner.nextLine();
                        currentLibrary.removeItem(temp.get(choice - 1));
                } // using method to remove item from Library
        }

        private static void viewBorrowedItems() {
                System.out.println(currentLibraryMember.getName() + " Borrowed Items: ");
                System.out.println("-".repeat(70));

                List<LibraryItem> temp = currentLibraryMember.getBorrowedItems();

                if (temp.isEmpty()) {
                        System.out.println("No items currently borrowed.");
                        return;
                }

                System.out.println("┌──────┬────────────────────────────────────────┬─────────────────┐");
                System.out.println("│ ID   │ Title                                  │ Type            │");
                System.out.println("├──────┼────────────────────────────────────────┼─────────────────┤");

                for (LibraryItem item : temp) {
                        String truncatedTitle = truncateTitle(item.getTitle(), 38); // Match the new width
                        System.out.printf("│ %-4d │ %-38s │ %-15s │%n",
                                        item.getId(), truncatedTitle, item.getItemType());
                }
                System.out.println("└──────┴────────────────────────────────────────┴─────────────────┘");
        }

        // private static void setStates(Scanner scanner, List<Library> libraries) {
        // flushScreen();

        // // Library Selection Title Screen
        // System.out.println("╔══════════════════════════════════════════════════════════════╗");
        // System.out.println("║ SELECT LIBRARY ║");
        // System.out.println("║ Choose Your Library Branch ║");
        // System.out.println("╚══════════════════════════════════════════════════════════════╝");
        // System.out.println();

        // System.out.println("┌─────────────────────────────────────────────────────────────┐");
        // System.out.println("│ AVAILABLE LIBRARIES │");
        // System.out.println("├─────────────────────────────────────────────────────────────┤");

        // int i = 1;
        // for (Library library : libraries) {
        // System.out.println("│ " + i + ". " + String.format("%-53s",
        // library.getLibraryName()) + " │");
        // System.out.println("│ " + String.format("%-51s",
        // library.getAddress().getStreet() + ", " +
        // library.getAddress().getCity() + ", " + library.getAddress().getState()) + "
        // │");
        // System.out.println("│ Items: " + String.format("%-3d",
        // library.getItems().size()) +
        // " | Members: " + String.format("%-34d", library.getMembers().size()) + "│");
        // if (i < libraries.size()) {
        // System.out.println("├─────────────────────────────────────────────────────────────┤");
        // }
        // i++;
        // }
        // System.out.println("└─────────────────────────────────────────────────────────────┘");
        // System.out.println();
        // System.out.print("Select Library (1-" + libraries.size() + "): ");

        // // Input validation for library selection
        // while (!scanner.hasNextInt()) {
        // System.out.println("\nInvalid input. Please enter a valid number.");
        // System.out.print("Select Library (1-" + libraries.size() + "): ");
        // scanner.next();
        // }
        // int temp = scanner.nextInt();
        // while (temp < 1 || temp > libraries.size()) {
        // System.out.println("\nInvalid choice. Please enter a number between 1 and " +
        // libraries.size() + ".");
        // System.out.print("Select Library (1-" + libraries.size() + "): ");
        // while (!scanner.hasNextInt()) {
        // System.out.println("\nInvalid input. Please enter a valid number.");
        // System.out.print("Select Library (1-" + libraries.size() + "): ");
        // scanner.next();
        // }
        // temp = scanner.nextInt();
        // }
        // scanner.nextLine();

        // currentLibrary = libraries.get(temp - 1);

        // // Confirmation message
        // flushScreen();

        // // Librarian Selection Title Screen
        // System.out.println("╔══════════════════════════════════════════════════════════════╗");
        // System.out.println("║ SELECT LIBRARIAN ║");
        // System.out.println("║ Choose Library Staff Account ║");
        // System.out.println("╚══════════════════════════════════════════════════════════════╝");
        // System.out.println();
        // System.out.println("Library: " + currentLibrary.getLibraryName());
        // System.out.println();

        // System.out.println("┌─────────────────────────────────────────────────────────────┐");
        // System.out.println("│ REGISTERED LIBRARIANS │");
        // System.out.println("├─────────────────────────────────────────────────────────────┤");

        // int z = 1;
        // for (Librarian librarian : currentLibrary.getLibrarians()) {
        // System.out.println("│ " + z + ". " + String.format("%-55s",
        // librarian.getName()) + " │");
        // System.out.println("│ " + String.format("%-55s", librarian.getEmail() + " | "
        // + librarian.getPhoneNumber()) + " │");
        // System.out.println("│ Employee ID: " + String.format("%-5s",
        // librarian.getEmployeeId()) + " | Salary: $" + String.format("%-20.2f",
        // librarian.getSalary()) + " │");
        // if (z < currentLibrary.getLibrarians().size()) {
        // System.out.println("├─────────────────────────────────────────────────────────────┤");
        // }
        // z++;
        // }
        // System.out.println("└─────────────────────────────────────────────────────────────┘");
        // System.out.println();
        // System.out.print("Select Librarian (1-" +
        // currentLibrary.getLibrarians().size() + "): ");

        // // Input validation for librarian selection
        // while (!scanner.hasNextInt()) {
        // System.out.println("\nInvalid input. Please enter a valid number.");
        // System.out.print("Select Librarian (1-" +
        // currentLibrary.getLibrarians().size() + "): ");
        // scanner.next();
        // }
        // int librarianChoice = scanner.nextInt();
        // while (librarianChoice < 1 || librarianChoice >
        // currentLibrary.getLibrarians().size()) {
        // System.out.println("\nInvalid choice. Please enter a number between 1 and " +
        // currentLibrary.getLibrarians().size() + ".");
        // System.out.print("Select Librarian (1-" +
        // currentLibrary.getLibrarians().size() + "): ");
        // while (!scanner.hasNextInt()) {
        // System.out.println("\nInvalid input. Please enter a valid number.");
        // System.out.print("Select Librarian (1-" +
        // currentLibrary.getLibrarians().size() + "): ");
        // scanner.next();
        // }
        // librarianChoice = scanner.nextInt();
        // }
        // scanner.nextLine();

        // Librarian selectedLibrarian =
        // currentLibrary.getLibrarians().get(librarianChoice - 1);

        // flushScreen();
        // // Confirmation message for librarian selection
        // System.out.println("\nLibrarian Selected: " + selectedLibrarian.getName());
        // System.out.println("-".repeat(50));
        // System.out.println();

        // // Member Selection Title Screen
        // System.out.println("╔══════════════════════════════════════════════════════════════╗");
        // System.out.println("║ SELECT MEMBER ║");
        // System.out.println("║ Choose Library Member Account ║");
        // System.out.println("╚══════════════════════════════════════════════════════════════╝");
        // System.out.println();

        // System.out.println("┌─────────────────────────────────────────────────────────────┐");
        // System.out.println("│ REGISTERED MEMBERS │");
        // System.out.println("├─────────────────────────────────────────────────────────────┤");

        // int y = 1;
        // for (LibraryMember member : currentLibrary.getMembers()) {
        // System.out.println("│ " + y + ". " + String.format("%-55s", member.getName())
        // + " │");
        // System.out.println("│ " + String.format("%-55s", member.getEmail() + " | " +
        // member.getPhoneNumber()) + " │");
        // if (y < currentLibrary.getMembers().size()) {
        // System.out.println("├─────────────────────────────────────────────────────────────┤");
        // }
        // y++;
        // }
        // System.out.println("└─────────────────────────────────────────────────────────────┘");
        // System.out.println();
        // System.out.print("Select Member (1-" + currentLibrary.getMembers().size() +
        // "): ");

        // // Input validation for member selection
        // while (!scanner.hasNextInt()) {
        // System.out.println("\nInvalid input. Please enter a valid number.");
        // System.out.print("Select Member (1-" + currentLibrary.getMembers().size() +
        // "): ");
        // scanner.next();
        // }
        // int x = scanner.nextInt();
        // while (x < 1 || x > currentLibrary.getMembers().size()) {
        // System.out.println("\nInvalid choice. Please enter a number between 1 and " +
        // currentLibrary.getMembers().size() + ".");
        // System.out.print("Select Member (1-" + currentLibrary.getMembers().size() +
        // "): ");
        // while (!scanner.hasNextInt()) {
        // System.out.println("\nInvalid input. Please enter a valid number.");
        // System.out.print("Select Member (1-" + currentLibrary.getMembers().size() +
        // "): ");
        // scanner.next();
        // }
        // x = scanner.nextInt();
        // }
        // scanner.nextLine();

        // currentLibraryMember = currentLibrary.getMembers().get(x - 1);
        // }

        private static void addItemToLibrary(Scanner scanner) {
                // Display library name
                System.out.println("Adding item to " + currentLibrary.getLibraryName());

                // Variable for itemTypeChoice
                String itemTypeChoice = "";
                LibraryItem newItem = null;

                // Loop until valid item type is selected
                boolean validItemType = false;
                while (!validItemType) {

                        flushScreen();

                        // Display title screen for item type selection
                        displaySectionDivider("Add New Item to Library");

                        System.out.println("┌─────────────────────────┐");
                        System.out.println("│     SELECT ITEM TYPE    │");
                        System.out.println("├─────────────────────────┤");
                        System.out.println("│  1. Book                │");
                        System.out.println("│  2. Music               │");
                        System.out.println("│  3. DVD                 │");
                        System.out.println("│  4. Periodical          │");
                        System.out.println("└─────────────────────────┘");
                        System.out.println();
                        System.out.println("Adding to: " + currentLibrary.getLibraryName());
                        System.out.println();
                        System.out.print("Enter your choice (1-4): ");

                        itemTypeChoice = scanner.nextLine().trim().toLowerCase();

                        try {
                                flushScreen();
                                // Get common details
                                System.out.print("Enter ID: ");
                                String idInput = scanner.nextLine().trim();

                                // Edge case: Empty ID
                                if (idInput.isEmpty()) {
                                        throw new IllegalArgumentException("ID cannot be empty.");
                                }

                                int id = Integer.parseInt(idInput);

                                // Edge case: Negative or zero ID
                                if (id <= 0) {
                                        throw new IllegalArgumentException("ID must be a positive number.");
                                }

                                // Edge case: Duplicate ID check
                                for (LibraryItem existingItem : currentLibrary.getItems()) {
                                        if (existingItem.getId() == id) {
                                                throw new IllegalArgumentException("An item with ID " + id
                                                                + " already exists: " + existingItem.getTitle());
                                        }
                                }

                                flushScreen();
                                System.out.print("Enter Title: ");
                                String title = scanner.nextLine().trim();

                                // Edge case: Empty title
                                if (title.isEmpty()) {
                                        throw new IllegalArgumentException("Title cannot be empty.");
                                }

                                // Select option
                                switch (itemTypeChoice) {
                                        case "1": // Book

                                                flushScreen();
                                                // Get book details
                                                System.out.print("Enter Author: ");
                                                String author = scanner.nextLine().trim();

                                                // Edge case -> if empty
                                                if (author.isEmpty()) {
                                                        throw new IllegalArgumentException("Author cannot be empty.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter ISBN: ");
                                                String isbn = scanner.nextLine().trim();

                                                // Edge case -> if empty
                                                if (isbn.isEmpty()) {
                                                        throw new IllegalArgumentException("ISBN cannot be empty.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Pages: ");
                                                String pagesInput = scanner.nextLine().trim();

                                                // Edge case -> if empty
                                                if (pagesInput.isEmpty()) {
                                                        throw new IllegalArgumentException(
                                                                        "Number of pages cannot be empty.");
                                                }

                                                // convert to Int
                                                int pages = Integer.parseInt(pagesInput);

                                                // Edge case -> Invalid page count - Input validated here
                                                if (pages <= 0 || pages > 10000) {
                                                        throw new IllegalArgumentException(
                                                                        "Number of pages must be between 1 and 10000.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Genre: ");
                                                String genreBook = scanner.nextLine().trim();

                                                // Edge case -> if empty
                                                if (genreBook.isEmpty()) {
                                                        throw new IllegalArgumentException("Genre cannot be empty.");
                                                }

                                                newItem = new Book((long) id, title, currentLibrary, author, isbn,
                                                                pages,
                                                                genreBook);
                                                validItemType = true; // Exit loop
                                                break;

                                        case "2":
                                                // Get music details
                                                flushScreen();
                                                System.out.print("Enter Artist: ");
                                                String artist = scanner.nextLine().trim();

                                                // Edge case -> if empty
                                                if (artist.isEmpty()) {
                                                        throw new IllegalArgumentException("Artist cannot be empty.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Release Date (DD-MM-YYYY): ");
                                                String releaseDate = scanner.nextLine().trim();

                                                // Edge case -> if empty
                                                if (releaseDate.isEmpty()) {
                                                        throw new IllegalArgumentException(
                                                                        "Release date cannot be empty.");
                                                }

                                                // Edge case -> Invalid date format - Input validated here
                                                if (!releaseDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                                                        throw new IllegalArgumentException(
                                                                        "Invalid date format. Use DD-MM-YYYY format.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Genre: ");
                                                String genreMusic = scanner.nextLine().trim();

                                                // Edge case -> if empty genre
                                                if (genreMusic.isEmpty()) {
                                                        throw new IllegalArgumentException("Genre cannot be empty.");
                                                }

                                                newItem = new Music((long) id, title, currentLibrary, artist,
                                                                releaseDate, genreMusic);
                                                validItemType = true; // Exit loop
                                                break;

                                        case "3":
                                                flushScreen();
                                                // Get DVD details
                                                System.out.print("Enter Director: ");
                                                String director = scanner.nextLine().trim();

                                                // Edge case -> if empty director
                                                if (director.isEmpty()) {
                                                        throw new IllegalArgumentException("Director cannot be empty.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Duration (in minutes): ");
                                                String durationInput = scanner.nextLine().trim();

                                                // Edge case: Empty duration
                                                if (durationInput.isEmpty()) {
                                                        throw new IllegalArgumentException("Duration cannot be empty.");
                                                }

                                                // convert to Int
                                                int durationDVD = Integer.parseInt(durationInput);

                                                // Edge case -> cant be less than or equal to 0 - Input validated here
                                                if (durationDVD <= 0 || durationDVD > 1000) {
                                                        throw new IllegalArgumentException(
                                                                        "Duration must be between 1 and 1000 minutes.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Rating (G, PG, PG-13, R): ");
                                                String rating = scanner.nextLine().trim().toUpperCase();

                                                // Edge case -> Empty rating
                                                if (rating.isEmpty()) {
                                                        throw new IllegalArgumentException("Rating cannot be empty.");
                                                }

                                                // Edge case -> Invalid rating - Input validated here
                                                if (!rating.matches("G|PG|PG-13|R|NC-17")) {
                                                        throw new IllegalArgumentException(
                                                                        "Invalid rating. Must be G, PG, PG-13, R, or NC-17.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Genre: ");
                                                String genreMovie = scanner.nextLine().trim();

                                                // Edge case -> Empty genre
                                                if (genreMovie.isEmpty()) {
                                                        throw new IllegalArgumentException("Genre cannot be empty.");
                                                }

                                                newItem = new DVD((long) id, title, currentLibrary, director,
                                                                durationDVD, rating, genreMovie);
                                                validItemType = true; // Exit loop
                                                break;
                                        case "4":
                                                flushScreen();
                                                // Get periodical details
                                                System.out.print("Enter Publisher: ");
                                                String publisher = scanner.nextLine().trim();

                                                // Edge case -> Empty publisher
                                                if (publisher.isEmpty()) {
                                                        throw new IllegalArgumentException(
                                                                        "Publisher cannot be empty.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter ISSN: ");
                                                String issn = scanner.nextLine().trim();

                                                // Edge case -> Empty ISSN
                                                if (issn.isEmpty()) {
                                                        throw new IllegalArgumentException("ISSN cannot be empty.");
                                                }

                                                // Edge case -> Invalid ISSN - Input validated here
                                                if (!issn.matches("\\d{4}-\\d{4}")) {
                                                        throw new IllegalArgumentException(
                                                                        "Invalid ISSN format. Use XXXX-XXXX format.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Volume: ");
                                                String volume = scanner.nextLine().trim();

                                                // Edge case -> Empty volume
                                                if (volume.isEmpty()) {
                                                        throw new IllegalArgumentException("Volume cannot be empty.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Issue: ");
                                                String issue = scanner.nextLine().trim();

                                                // Edge case -> Empty issue
                                                if (issue.isEmpty()) {
                                                        throw new IllegalArgumentException("Issue cannot be empty.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Publication Date (YYYY-MM-DD): ");
                                                String publicationDate = scanner.nextLine().trim();

                                                // Edge case -> Empty publication date
                                                if (publicationDate.isEmpty()) {
                                                        throw new IllegalArgumentException(
                                                                        "Publication date cannot be empty.");
                                                }

                                                // Edge case -> Invalid date format - Input validated here
                                                if (!publicationDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                                                        throw new IllegalArgumentException(
                                                                        "Invalid date format. Use YYYY-MM-DD format.");
                                                }

                                                newItem = new Periodical((long) id, title, currentLibrary, publisher,
                                                                issn, volume, issue, publicationDate);
                                                validItemType = true; // Exit loop
                                                break;

                                        default:
                                                System.out.println("Invalid item type. Please enter 1, 2, 3, or 4.");
                                                System.out.println("Press Enter to continue...");
                                                scanner.nextLine(); // Loop will continue, validItemType remains false
                                }

                                // Add the new item to the library collection
                                if (newItem != null) {
                                        currentLibrary.addItem(newItem);
                                        flushScreen();
                                        System.out.println("Item added successfully.");
                                        System.out.println("\nNewly added item:");
                                        System.out.println(newItem);
                                        System.out.println("\nPress Enter to continue...");
                                        scanner.nextLine();
                                }

                        } catch (NumberFormatException e) {
                                System.out.println(
                                                "Error: Invalid number format. Please enter valid numbers for ID, pages, or duration.");
                                System.out.println("Press Enter to try again...");
                                scanner.nextLine(); // Loop will continue, validItemType remains false
                        } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.out.println("Press Enter to try again...");
                                scanner.nextLine(); // Loop will continue, validItemType remains false
                        } catch (Exception e) {
                                System.out.println("Unexpected error: " + e.getMessage());
                                System.out.println("Press Enter to try again...");
                                scanner.nextLine(); // Loop will continue, validItemType remains false
                        }
                }
        }

        private static void selectLibraryOnly(Scanner scanner, List<Library> libraries) {
                flushScreen();

                // Library Selection Title Screen
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                     SELECT LIBRARY                           ║");
                System.out.println("║                 Choose Your Library Branch                   ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();

                System.out.println("┌─────────────────────────────────────────────────────────────┐");
                System.out.println("│                    AVAILABLE LIBRARIES                      │");
                System.out.println("├─────────────────────────────────────────────────────────────┤");

                int i = 1;
                for (Library library : libraries) {
                        System.out.println(
                                        "│  " + i + ". " + String.format("%-53s", library.getLibraryName()) + "   │");
                        System.out.println("│     " + String.format("%-51s", library.getAddress().getStreet() + ", " +
                                        library.getAddress().getCity() + ", " + library.getAddress().getState())
                                        + "     │");
                        System.out.println("│     Items: " + String.format("%-3d", library.getItems().size()) +
                                        " | Members: " + String.format("%-34d", library.getMembers().size()) + "│");
                        if (i < libraries.size()) {
                                System.out.println("├─────────────────────────────────────────────────────────────┤");
                        }
                        i++;
                }
                System.out.println("└─────────────────────────────────────────────────────────────┘");
                System.out.println();
                System.out.print("Select Library (1-" + libraries.size() + "): ");

                // Input validation for library selection
                while (!scanner.hasNextInt()) {
                        System.out.println("\nInvalid input. Please enter a valid number.");
                        System.out.print("Select Library (1-" + libraries.size() + "): ");
                        scanner.next();
                }

                int temp = scanner.nextInt();
                while (temp < 1 || temp > libraries.size()) {
                        System.out.println("\nInvalid choice. Please enter a number between 1 and " + libraries.size()
                                        + ".");
                        System.out.print("Select Library (1-" + libraries.size() + "): ");
                        while (!scanner.hasNextInt()) {
                                System.out.println("\nInvalid input. Please enter a valid number.");
                                System.out.print("Select Library (1-" + libraries.size() + "): ");
                                scanner.next();
                        }
                        temp = scanner.nextInt();
                }
                scanner.nextLine();

                currentLibrary = libraries.get(temp - 1);

                // Confirmation message
                flushScreen();
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                   LIBRARY SELECTED                           ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();
                System.out.println("Selected Library: " + currentLibrary.getLibraryName());
                System.out.println("Address: " + currentLibrary.getAddress().getStreet() + ", " +
                                currentLibrary.getAddress().getCity() + ", " + currentLibrary.getAddress().getState());
                System.out.println();
                System.out.println("Proceeding to main menu...");

                try {
                        Thread.sleep(1500); // Brief pause for user to see confirmation
                } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                }
        }

        private static void selectLibrarianOnly(Scanner scanner) {
                flushScreen();
                // Librarian Selection Title Screen
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                    SELECT LIBRARIAN                          ║");
                System.out.println("║              Choose Library Staff Account                    ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();
                System.out.println("Library: " + currentLibrary.getLibraryName());
                System.out.println();

                System.out.println("┌─────────────────────────────────────────────────────────────┐");
                System.out.println("│                   REGISTERED LIBRARIANS                     │");
                System.out.println("├─────────────────────────────────────────────────────────────┤");

                int z = 1;
                for (Librarian librarian : currentLibrary.getLibrarians()) {
                        System.out.println("│  " + z + ". " + String.format("%-55s", librarian.getName()) + " │");
                        System.out.println("│     "
                                        + String.format("%-55s",
                                                        librarian.getEmail() + " | " + librarian.getPhoneNumber())
                                        + " │");
                        System.out.println("│     Employee ID: " + String.format("%-5s", librarian.getEmployeeId())
                                        + "    | Salary: $" + String.format("%-20.2f", librarian.getSalary()) + "   │");
                        if (z < currentLibrary.getLibrarians().size()) {
                                System.out.println("├─────────────────────────────────────────────────────────────┤");
                        }
                        z++;
                }
                System.out.println("└─────────────────────────────────────────────────────────────┘");
                System.out.println();
                System.out.print("Select Librarian (1-" + currentLibrary.getLibrarians().size() + "): ");

                // Input validation for librarian selection
                while (!scanner.hasNextInt()) {
                        System.out.println("\nInvalid input. Please enter a valid number.");
                        System.out.print("Select Librarian (1-" + currentLibrary.getLibrarians().size() + "): ");
                        scanner.next();
                }
                int librarianChoice = scanner.nextInt();
                while (librarianChoice < 1 || librarianChoice > currentLibrary.getLibrarians().size()) {
                        System.out.println("\nInvalid choice. Please enter a number between 1 and "
                                        + currentLibrary.getLibrarians().size() + ".");
                        System.out.print("Select Librarian (1-" + currentLibrary.getLibrarians().size() + "): ");
                        while (!scanner.hasNextInt()) {
                                System.out.println("\nInvalid input. Please enter a valid number.");
                                System.out.print(
                                                "Select Librarian (1-" + currentLibrary.getLibrarians().size() + "): ");
                                scanner.next();
                        }
                        librarianChoice = scanner.nextInt();
                }
                scanner.nextLine();

                Librarian selectedLibrarian = currentLibrary.getLibrarians().get(librarianChoice - 1);

                // // Confirmation message for librarian selection
                // System.out.println("\nLibrarian Selected: " + selectedLibrarian.getName());
                // System.out.println("-".repeat(50));
                // System.out.println();

                // //Ask do you want to select a member?
                // System.out.print("Do you want to select a member? (yes/no): ");
                // String response = scanner.nextLine().trim().toLowerCase();

                // //ASK IF THEY WANT TO SELECT A MEMBER
                // if (response.equals("yes")) {
                // // Now select member
                // selectMemberOnly(scanner);
                // } else if (response.equals("no")) {

                // Proceed to librarian portal
                flushScreen();
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                   LIBRARIAN SELECTED                         ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();
                System.out.println("Selected Librarian: " + selectedLibrarian.getName());
                System.out.println("Employee ID: " + selectedLibrarian.getEmployeeId());
                System.out.println();
                System.out.println("Proceeding to librarian portal...");

                try {
                        Thread.sleep(1500); // Brief pause for user to see confirmation
                } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                }
        }

        private static void selectMemberOnly(Scanner scanner) {
                flushScreen();
                // Member Selection Title Screen
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                     SELECT MEMBER                            ║");
                System.out.println("║              Choose Library Member Account                   ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();

                System.out.println("┌─────────────────────────────────────────────────────────────┐");
                System.out.println("│                    REGISTERED MEMBERS                       │");
                System.out.println("├─────────────────────────────────────────────────────────────┤");

                int y = 1;
                for (LibraryMember member : currentLibrary.getMembers()) {
                        System.out.println("│  " + y + ". " + String.format("%-55s", member.getName()) + " │");
                        System.out.println("│     "
                                        + String.format("%-55s", member.getEmail() + " | " + member.getPhoneNumber())
                                        + " │");
                        if (y < currentLibrary.getMembers().size()) {
                                System.out.println("├─────────────────────────────────────────────────────────────┤");
                        }
                        y++;
                }
                System.out.println("└─────────────────────────────────────────────────────────────┘");
                System.out.println();
                System.out.print("Select Member (1-" + currentLibrary.getMembers().size() + "): ");

                // Input validation for member selection
                while (!scanner.hasNextInt()) {
                        System.out.println("\nInvalid input. Please enter a valid number.");
                        System.out.print("Select Member (1-" + currentLibrary.getMembers().size() + "): ");
                        scanner.next();
                }
                int x = scanner.nextInt();
                while (x < 1 || x > currentLibrary.getMembers().size()) {
                        System.out.println("\nInvalid choice. Please enter a number between 1 and "
                                        + currentLibrary.getMembers().size() + ".");
                        System.out.print("Select Member (1-" + currentLibrary.getMembers().size() + "): ");
                        while (!scanner.hasNextInt()) {
                                System.out.println("\nInvalid input. Please enter a valid number.");
                                System.out.print("Select Member (1-" + currentLibrary.getMembers().size() + "): ");
                                scanner.next();
                        }
                        x = scanner.nextInt();
                }
                scanner.nextLine();

                currentLibraryMember = currentLibrary.getMembers().get(x - 1);

                // Confirmation message
                flushScreen();
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                   MEMBER SELECTED                            ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();
                System.out.println("Selected Member: " + currentLibraryMember.getName());
                System.out.println("Email: " + currentLibraryMember.getEmail());
                System.out.println();
                System.out.println("Proceeding to member portal...");

                try {
                        Thread.sleep(1500);
                } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                }
        }

        // fix long titles names for formatting
        private static String truncateTitle(String title, int maxLength) {
                if (title == null)
                        return "";
                if (title.length() <= maxLength)
                        return title;
                return title.substring(0, maxLength - 3) + "...";
        }
}
