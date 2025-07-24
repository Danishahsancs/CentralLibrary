package com.zipcodewilmington.centrallibrary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by n3pjk on 6/9/2025.
 */
public class MainApplication {

        public static void main(String[] args) {
                Library library1 = new Library("Library1", new Address("12 abc st", "wilmington", "Delaware", "19801"));
                LibraryMember jounish = new LibraryMember("Jounish", 22, "Jounish@email.com", "123-456-7890", 1,
                                LocalDate.now().toString(),
                                new Address("12 jump st", "new castle", "Delaware", "19720"));
                Librarian librarian = new Librarian("librarian", 67, "librarian@library.com", "098-765-4321", "1",
                                "front desk",
                                30000, null);

                Book book1 = new Book(1L, "The Great Gatsby", library1, "F. Scott Fitzgerald", "9780743273565", 180,
                                "Classic");
                Book book2 = new Book(2L, "1984", library1, "George Orwell", "9780451524935", 328, "Dystopian");
                DVD dvd1 = new DVD(3L, "Inception", library1, "Christopher Nolan", 148, "PG-13", "Sci-Fi");
                DVD dvd2 = new DVD(4L, "The Godfather", library1, "Francis Ford Coppola", 175, "R", "Crime");
                Periodical p1 = new Periodical(5L, "National Geographic", library1, "NatGeo Society", "0027-9358",
                                "Vol. 245",
                                "No. 6", "2025-06");
                Periodical p2 = new Periodical(6L, "Time Magazine", library1, "Time Inc.", "0040-781X", "Vol. 198",
                                "No. 12",
                                "2025-07");
                Music music1 = new Music(7L, "Thriller", library1, "Michael Jackson", "1982-11-30", "Pop");
                Music music2 = new Music(8L, "Back in Black", library1, "AC/DC", "1980-07-25", "Rock");

                library1.addLibrarian(librarian);
                library1.addMember(jounish);
                library1.addItem(book1);
                library1.addItem(book2);
                library1.addItem(dvd1);
                library1.addItem(dvd2);
                librarian.addItemToLibrary(library1, p1);
                librarian.addItemToLibrary(library1, p2);
                librarian.addItemToLibrary(library1, music1);
                librarian.addItemToLibrary(library1, music2);

                library1.removeItem(book1);
                librarian.removeItemFromLibrary(library1, p1);

                jounish.borrowItem(music2);
                jounish.reserveItem(music1);

                // library1.displayAllItems();
                // System.out.println("\n"+library1);
                // System.out.println("\n"+librarian);
                // System.out.println("\n"+jounish);

                jounish.returnItem(music2, 20);
                jounish.removeReservedItem(music1);
                // System.out.println("\n"+jounish);
                Scanner scanner = new Scanner(System.in);
                List<Library> libraries = new ArrayList<>();
                libraries.add(library1);
                int choice = -1;
                do {
                        System.out.println("0. Quit\n1. Librarian\n2. Library Member\n");
                        System.out.print("Select Option:");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                                case 1:
                                        librarianOptions(scanner, libraries);
                                        break;
                                case 2:
                                        libraryMemberOptions(scanner, libraries);
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

        private static void librarianOptions(Scanner scanner, List<Library> libraries) {
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
                                        for (Library library : libraries) {
                                                library.displayAllItems();
                                        }
                                        break;
                                case 2:
                                        flushScreen();
                                        System.out.print("Enter search parameter: ");
                                        String keyword = scanner.nextLine();
                                        for (Library library : libraries) {
                                                System.out.println(library.getLibraryName() + "'s Results:");
                                                List<LibraryItem> temp = library.search(keyword);
                                                for (LibraryItem item : temp)
                                                        System.out.println(item);
                                        }
                                        break;
                                case 3:
                                        flushScreen();
                                        int i = 1;
                                        for (Library library : libraries) {
                                                System.out.println(i + ". " + library.getLibraryName());
                                        }
                                        System.out.print("Select choice: ");
                                        int temp = scanner.nextInt();
                                        scanner.nextLine();
                                        addItemToLibrary(scanner, libraries.get(temp - 1));
                                        break;
                                case 4:
                                        flushScreen();
                                        int y = 1;
                                        for (Library library : libraries) {
                                                System.out.println(y + ". " + library.getLibraryName());
                                        }
                                        System.out.print("Select choice: ");
                                        int x = scanner.nextInt();
                                        scanner.nextLine();
                                        removeItemfromLibrary(scanner, libraries.get(x - 1));
                                        break;
                                case 5:
                                        flushScreen();
                                        for (Library library : libraries) {
                                                System.out.println(library.getLibraryName() + "'s late fee report:");
                                                library.generateReportItems();
                                        }
                                        break;
                                default:
                                        break;
                        }
                } while (choice != 0);

        }

        private static void libraryMemberOptions(Scanner scanner, List<Library> libraries) {
                flushScreen();

                int choice = -1;
                do {
                        System.out.print(
                                        "0. go back\n1. view borrowed items\n2. search for item \n3. checkout item\n4. check in item\n5. reserve item\n6. cancel reservation\n7. pay fees\n\nSelect Option: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                                case 1:

                                        break;
                                case 2:

                                        break;
                                case 3:

                                        break;
                                case 4:

                                        break;
                                case 5:

                                        break;
                                case 6:

                                        break;
                                case 7:

                                        break;
                                default:
                                        break;
                        }
                } while (choice != 0);
        }

        private static void addItemToLibrary(Scanner scanner, Library librarychoice) {
                System.out.println(librarychoice);
        }

        private static void removeItemfromLibrary(Scanner scanner, Library library) {
                
        }
}
