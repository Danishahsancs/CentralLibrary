package com.zipcodewilmington.centrallibrary;

import java.time.LocalDate;

/**
 * Created by n3pjk on 6/9/2025.
 */
public class MainApplication {

    
    public static void main(String[] args) {
        Library library1 = new Library("Library1", new Address("12 abc st", "wilmington", "Delaware", "19801"));
    LibraryMember jounish = new LibraryMember("Jounish", 22, "Jounish@email.com", "123-456-7890", 1,
            LocalDate.now().toString(), new Address("12 jump st", "new castle", "Delaware", "19720"));
    Librarian librarian = new Librarian("librarian", 67, "librarian@library.com", "098-765-4321", "1", "front desk",
            30000, null);

    Book book1 = new Book(1L, "The Great Gatsby", library1, "F. Scott Fitzgerald", "9780743273565", 180, "Classic");
    Book book2 = new Book(2L, "1984", library1, "George Orwell", "9780451524935", 328, "Dystopian");
    DVD dvd1 = new DVD(3L, "Inception", library1, "Christopher Nolan", 148, "PG-13", "Sci-Fi");
    DVD dvd2 = new DVD(4L, "The Godfather", library1, "Francis Ford Coppola", 175, "R", "Crime");
    Periodical p1 = new Periodical(5L, "National Geographic", library1, "NatGeo Society", "0027-9358", "Vol. 245",
            "No. 6", "2025-06");
    Periodical p2 = new Periodical(6L, "Time Magazine", library1, "Time Inc.", "0040-781X", "Vol. 198", "No. 12",
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
    jounish.reserveItem(music2);
    

    System.out.println("\n"+library1);
    System.out.println("\n"+librarian);
    System.out.println("\n"+jounish);


    }

}
