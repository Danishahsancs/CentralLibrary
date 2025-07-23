package com.zipcodewilmington.centrallibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class InheritanceTest {

    //----- instance variables -----

    private Book book;
    private Periodical periodical;
    private DVD dvd;
    private Music music;
    private LibraryMember libraryMember;
    private Address memberAddress;


    @BeforeEach
    void setUp() {
        book = new Book(1234L, "Title", new Library("Library", null), "Younis", "isbn", 256, "Horror");
        periodical = new Periodical(1234L, "Title", new Library("Library", null), "publisher", "issn", "volume", "issuenumber", "date");
        dvd = new DVD(1001L, "Superbad", new Library("Library", null), "Greg Mottola", 113, "R", "Comedy");
        music =  new Music(1234L, "Title", new Library("Library", null), "Younis", "1995", "Pop");

        libraryMember = new LibraryMember("Jenn Staci", 29, "jenn@example.com", "555-1234", 1L, "2023-01-01", memberAddress);
        memberAddress = new Address("123 Main St", "Springfield", "IL", "62701");
    }


    //----- Library Item instanceOf tests -----

    @Test
    @DisplayName("Book is an instanceof LibraryItem")
    void testBookIsLibraryItem() {

        assertTrue(book instanceof LibraryItem, "Book should be an instance of LibraryItem");
    }

    @Test
    @DisplayName("Periodical is an instanceof LibraryItem")
    void testPeriodicalIsLibraryItem() {

        assertTrue(periodical instanceof LibraryItem, "Periodical should be an instance of LibraryItem");
    }

    @Test
    @DisplayName("DVD is an instanceof LibraryItem")
    void testDVDIsLibraryItem() {

        assertTrue(dvd instanceof LibraryItem, "DVD should be an instance of LibraryItem");
    }

    @Test
    @DisplayName("Music is an instanceof LibraryItem")
    void testMusicIsLibraryItem() {

        assertTrue(music instanceof LibraryItem, "Music should be an instance of LibraryItem");
    }


     //----- Searchable instanceOf tests -----

    @Test
    @DisplayName("All LibraryItems are instances of Searchable")
    void testAllLibraryItemsAreSearchable() {
        
        assertTrue(book instanceof Searchable, "Book should be an instance of Searchable");
        assertTrue(periodical instanceof Searchable, "Periodical should be an instance of Searchable");
        assertTrue(dvd instanceof Searchable, "DVD should be an instance of Searchable");
        assertTrue(music instanceof Searchable, "Music should be an instance of Searchable");
    }


    //----- Reservable instanceOf tests -----

    @Test
    @DisplayName("Book is an instanceof Reservable")
    void testBookIsReservable() {
        assertTrue(book instanceof Reservable, "Book should be an instance of Reservable");
    }

    // Periodical is NOT an instanceof Reservable!
    @Test
    @DisplayName("Periodical is NOT an instanceof Reservable")
    void testPeriodicalIsReservable() {
        assertFalse(periodical instanceof Reservable, "Periodical should NOT be an instance of Reservable");
    }

    @Test
    @DisplayName("DVD is an instanceof Reservable")
    void testDVDIsReservable() {
        assertTrue(dvd instanceof Reservable, "DVD should be an instance of Reservable");
    }

    @Test
    @DisplayName("Music is NOT an instanceof Reservable")
    void testMusicIsReservable() {
        assertFalse(music instanceof Reservable, "Music should NOT be an instance of Reservable");
    }


    //----- Inheritance tests -----
    
    @Test
    @DisplayName("All LibraryItems inherit isAvailable, checkOut, checkIn methods")
    void testInheritedBehavior() {

        assertTrue(book.isAvailable());
        book.checkOut();
        assertFalse(book.isAvailable());
        book.checkIn();
        assertTrue(book.isAvailable());
        
        assertTrue(dvd.isAvailable());
        dvd.checkOut();
        assertFalse(dvd.isAvailable());
        dvd.checkIn();
        assertTrue(dvd.isAvailable());

        assertTrue(music.isAvailable());
        music.checkOut();
        assertFalse(music.isAvailable());
        music.checkIn();
        assertTrue(music.isAvailable());
    }

    
    @Test
    @DisplayName("Late fee calculation is inherited by all LibraryItems")
    void testLateFeeCalculationInheritance() {

        // Books: 7 DVD: 5 Music: 3 Periodical: 1
        assertEquals(0.0, book.calculateLateFee(7));
        assertEquals(0.0, dvd.calculateLateFee(5));
        assertEquals(0.0, music.calculateLateFee(3));
        assertEquals(0.0, periodical.calculateLateFee(1));

        // Books: 20 DVD: 15 Music: 10 Periodical: 5
        assertTrue(book.calculateLateFee(20) > 0);
        assertTrue(dvd.calculateLateFee(15) > 0);
        assertTrue(music.calculateLateFee(10) > 0);
    }

    @Test
    @DisplayName("All items can be checked out/in")
    void testCheckoutBehavior() {

        LibraryItem[] items = {book, dvd, music, periodical};
        
        for (LibraryItem item : items) {
            assertTrue(item.isAvailable());
            item.checkOut();
            assertFalse(item.isAvailable());
            item.checkIn();
            assertTrue(item.isAvailable());

        }
    }


      //----- Method overriding tests -----
    
    @Test
    @DisplayName("getItemType method is properly overridden ")
    void testGetItemTypeOverriding() {

        assertEquals("Book", book.getItemType());
        assertEquals("DVD", dvd.getItemType());
        assertEquals("Music", music.getItemType());
        assertEquals("Periodical", periodical.getItemType());
    }

    @Test
    @DisplayName("Test getMaxBorrowDays method overriding")
    void testGetMaxBorrowDays() {

        // Books: 14 Periodical: 7 DVD: 7 Music: 7
        assertEquals(14, book.getMaxBorrowDays(), "Book max borrow days should be 14.");
        assertEquals(7, periodical.getMaxBorrowDays(), "Periodical max borrow days should be 7.");
        assertEquals(7, dvd.getMaxBorrowDays(), "DVD max borrow days should be 7.");
        assertEquals(7, music.getMaxBorrowDays(), "Music max borrow days should be 7.");
    }

}
