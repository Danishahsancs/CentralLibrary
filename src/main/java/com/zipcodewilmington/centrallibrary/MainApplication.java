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
        private static LibraryMember currentLibraryMember = null;

        public static void main(String[] args) {
                Library library1 = new Library("Library1", new Address("12 abc st", "wilmington", "Delaware", "19801"));
                LibraryMember jounish = new LibraryMember("Jounish", 22, "Jounish@email.com", "123-456-7890", 1,
                                LocalDate.now().toString(),
                                new Address("12 jump st", "new castle", "Delaware", "19720"));
                Librarian librarian = new Librarian("librarian", 67, "librarian@library.com", "098-765-4321", "1",
                                "front desk", 30000, null);

                library1.addLibrarian(librarian);
                library1.addMember(jounish);

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

                setStates(scanner, libraries);

                int choice = -1;
                do {
                        flushScreen();
                        System.out.println(
                                        "0. Quit\n1. Librarian\n2. Library Member\n3. Change Library and Member choice");
                        System.out.print("Select Option:");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                                case 1:
                                        librarianOptions(scanner);
                                        break;
                                case 2:
                                        libraryMemberOptions(scanner);
                                        break;
                                case 3:
                                        flushScreen();
                                        setStates(scanner, libraries);
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

        private static void librarianOptions(Scanner scanner) {
                flushScreen();

                int choice = -1;
                do {
                        System.out.print(
                                        "\n0. go back\n1. view all items\n2. search for item\n3. add item to library\n4. remove item from library\n5. print late fee report \n\nSelect Option: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                                case 1:
                                        flushScreen();
                                        currentLibrary.displayAllItems();
                                        break;
                                case 2:
                                        flushScreen();
                                        System.out.print("Enter search parameter: ");
                                        String keyword = scanner.nextLine();
                                        System.out.println(currentLibrary.getLibraryName() + "'s Results:");
                                        List<LibraryItem> temp = currentLibrary.search(keyword);
                                        for (LibraryItem item : temp)
                                                System.out.println(item.getItemType() + ":\t" + item.getTitle()+"\n");
                                        break;
                                case 3:
                                        flushScreen();
                                        addItemToLibrary(scanner);
                                        break;
                                case 4:
                                        flushScreen();
                                        removeItemfromLibrary(scanner);
                                        break;
                                case 5:
                                        flushScreen();
                                        System.out.println(currentLibrary.getLibraryName() + "'s late fee report:");
                                        currentLibrary.generateReportItems();
                                        break;
                                default:
                                        break;
                        }
                } while (choice != 0);
        }

        private static void libraryMemberOptions(Scanner scanner) {
                flushScreen();

                List<LibraryItem> temp;

                int choice = -1;
                do {
                        System.out.print(
                                        "\n0. go back\n1. view borrowed items\n2. search for item \n3. checkout item\n4. check in item\n5. reserve item\n6. cancel reservation\n7. pay fees\n\nSelect Option: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                                case 1:
                                        flushScreen();
                                        viewBorrowedItems();
                                        break;
                                case 2:
                                        flushScreen();
                                        System.out.print("Enter search parameter: ");
                                        String keyword = scanner.nextLine();
                                        System.out.println(currentLibrary.getLibraryName() + "'s Results:");
                                        temp = currentLibrary.search(keyword);
                                        for (LibraryItem item : temp)
                                                System.out.println(item.getItemType() + ":\t" + item.getTitle()+"\n");
                                        break;
                                case 3:
                                        flushScreen();
                                        checkout(scanner);
                                        break;
                                case 4:
                                        flushScreen();
                                        checkInItemfromMember(scanner);
                                        break;
                                case 5:
                                        flushScreen();
                                        reserve(scanner);
                                        break;
                                case 6:
                                        flushScreen();
                                        temp = currentLibraryMember.getReservedItems();
                                        int i = 0;
                                        for (LibraryItem item : temp) {
                                                System.out.println(i + ". " + item.getTitle());
                                                i++;
                                        }
                                        int x = scanner.nextInt();
                                        scanner.nextLine();
                                        currentLibraryMember.removeReservedItem(temp.get(x - 1));
                                        break;
                                case 7:
                                        System.out.println("Member: " + currentLibraryMember.getName() + " Amount Owed:"
                                                        + currentLibraryMember.getoutstandingFees());
                                        System.out.print("How much would you like to pay: ");
                                        Double amount = scanner.nextDouble();
                                        scanner.nextLine();
                                        currentLibraryMember.payFees(amount);
                                        break;
                                default:
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
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Please enter number of days late: ");
                        int late = scanner.nextInt();
                        scanner.nextLine();
                        currentLibraryMember.returnItem(temp.get(choice - 1), late);
                }

        }

        private static List<LibraryItem> displayItemsChoice(Scanner scanner) {
                int i = 1;
                System.out.print("Enter search parameter: ");
                String keyword = scanner.nextLine();
                System.out.println(currentLibrary.getLibraryName() + "'s Results:");
                List<LibraryItem> temp = currentLibrary.search(keyword);
                for (LibraryItem item : temp) {
                        System.out.println(i + ". " + item+"\n");
                        i++;
                }

                return temp;
        }

        private static void reserve(Scanner scanner) {
                List<LibraryItem> temp = displayItemsChoice(scanner);
                if (temp.size() != 0) {
                        System.out.println("Please enter number of item you would like to checkout:");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        currentLibraryMember.reserveItem(temp.get(choice - 1));
                }
        }

        private static void checkout(Scanner scanner) {

                List<LibraryItem> temp = displayItemsChoice(scanner);
                if (temp.size() != 0) {
                        System.out.println("Please enter number of item you would like to checkout:");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        currentLibraryMember.borrowItem(temp.get(choice - 1));
                }
        }

        private static void removeItemfromLibrary(Scanner scanner) {

                List<LibraryItem> temp = displayItemsChoice(scanner);
                if (temp.size() != 0) {
                        System.out.println("Please enter number of item you would like to remove:");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        currentLibrary.removeItem(temp.get(choice - 1));
                } // using method to remove item from Library

        }

        private static void viewBorrowedItems() {

                System.out.println(currentLibraryMember.getName() + " borrowed items: ");
                List<LibraryItem> temp = currentLibraryMember.getBorrowedItems();

                for (LibraryItem item : temp) {
                        System.out.println("Title: " + item.getTitle() + " ID: " + item.getId());
                }

        }

        private static void setStates(Scanner scanner, List<Library> libraries) {
                int i = 1;
                for (Library library : libraries) {
                        System.out.println(i + ". " + library.getLibraryName());
                        i++;
                }
                System.out.print("Select Library: ");
                int temp = scanner.nextInt();
                scanner.nextLine();

                currentLibrary = libraries.get(temp - 1);

                int y = 1;
                for (LibraryMember member : currentLibrary.getMembers()) {
                        System.out.println(y + ". " + member.getName());
                        y++;
                }
                System.out.print("Select Member: ");
                int x = scanner.nextInt();
                scanner.nextLine();
                currentLibraryMember = currentLibrary.getMembers().get(x - 1);

                System.out.println(currentLibrary + ": " + currentLibraryMember);
        }

        private static void addItemToLibrary(Scanner scanner) {

                // Display library name
                System.out.println("Adding item to " + currentLibrary.getLibraryName());

                // Variable for itemTypeChoice
                String itemTypeChoice = "";
                LibraryItem newItem = null;

                // Loop until valid item type is selected
                boolean validItemType = false;
                while (!validItemType) {

                        // Clear the screen
                        flushScreen();

                        // Give option for book, music, dvd or periodical
                        System.out.print("Select item type:\n 1: Book\n 2: Music\n 3: DVD\n 4: Periodical\n ");
                        System.out.print("Enter Choice: ");

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

                                                System.out.print("Enter ISBN: ");
                                                String isbn = scanner.nextLine().trim();

                                                // Edge case -> if empty
                                                if (isbn.isEmpty()) {
                                                        throw new IllegalArgumentException("ISBN cannot be empty.");
                                                }

                                                System.out.print("Enter Pages: ");
                                                String pagesInput = scanner.nextLine().trim();

                                                // Edge case -> if empty
                                                if (pagesInput.isEmpty()) {
                                                        throw new IllegalArgumentException(
                                                                        "Number of pages cannot be empty.");
                                                }

                                                // convert to Int
                                                int pages = Integer.parseInt(pagesInput);

                                                // Edge case -> Invalid page count
                                                if (pages <= 0) {
                                                        throw new IllegalArgumentException(
                                                                        "Number of pages must be greater than 0.");
                                                }

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
                                                System.out.print("Enter Release Date (YYYY-MM-DD): ");
                                                String releaseDate = scanner.nextLine().trim();

                                                // Edge case -> if empty
                                                if (releaseDate.isEmpty()) {
                                                        throw new IllegalArgumentException(
                                                                        "Release date cannot be empty.");
                                                }

                                                // Edge case -> Invalid date format
                                                if (!releaseDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                                        throw new IllegalArgumentException(
                                                                        "Invalid date format. Use YYYY-MM-DD format.");
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

                                                // Edge case -> cant be less than or equal to 0
                                                if (durationDVD <= 0) {
                                                        throw new IllegalArgumentException(
                                                                        "Duration must be greater than 0 minutes.");
                                                }

                                                flushScreen();
                                                System.out.print("Enter Rating (G, PG, PG-13, R): ");
                                                String rating = scanner.nextLine().trim().toUpperCase();

                                                // Edge case -> Empty rating
                                                if (rating.isEmpty()) {
                                                        throw new IllegalArgumentException("Rating cannot be empty.");
                                                }

                                                // Edge case -> Invalid rating
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

                                                // Edge case -> Invalid ISSN
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

                                                // Edge case -> Invalid date format
                                                if (!publicationDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
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

}
