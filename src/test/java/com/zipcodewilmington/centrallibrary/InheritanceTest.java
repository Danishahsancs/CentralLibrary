package com.zipcodewilmington.centrallibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InheritanceTest {

    //----- instance variables -----

    private Book book;
    private Periodical periodical;
    private DVD dvd;
    private Music music;


    @BeforeEach
    void setUp() {
        book = new Book(1234L, "Title", new Library("Library", null), "Younis", "isbn", 256, "Horror");
        periodical = new Periodical(1234L, "Title", new Library("Library", null), "publisher", "issn", "volume", "issuenumber", "date");
        dvd = new DVD(1001L, "Superbad", new Library("Library", null), "Greg Mottola", 113, "R", "Comedy");
        music =  new Music(1234L, "Title", new Library("Library", null), "Younis", "1995", "Pop");
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

    @Test
    @DisplayName("Searchable interface methods work")
    void testSearchableInterfaceMethods() {
        
        // Test -> getSearchableFields returns non-null & non-empty lists
        assertNotNull(book.getSearchableFields(), "Book should have searchable fields");
        assertFalse(book.getSearchableFields().isEmpty(), "Book searchable fields should not be empty");
        
        assertNotNull(dvd.getSearchableFields(), "DVD should have searchable fields");
        assertFalse(dvd.getSearchableFields().isEmpty(), "DVD searchable fields should not be empty");
        
        assertNotNull(music.getSearchableFields(), "Music should have searchable fields");
        assertFalse(music.getSearchableFields().isEmpty(), "Music searchable fields should not be empty");
        
        assertNotNull(periodical.getSearchableFields(), "Periodical should have searchable fields");
        assertFalse(periodical.getSearchableFields().isEmpty(), "Periodical searchable fields should not be empty");
        
        // Test -> matchesKeyword (from interface)
        assertTrue(book.matchesKeyword("Title"), "Book should match keyword 'Title'");
        assertTrue(dvd.matchesKeyword("Superbad"), "DVD should match keyword 'Superbad'");
        assertFalse(book.matchesKeyword("NonexistentKeyword"), "Book should not match nonexistent keyword");
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

    @Test
    @DisplayName("Book toString method is properly overridden")
    void testBookToString() {

        String result = book.toString();
        
        assertNotNull(result, "toString should not return null");
        assertTrue(result.contains("Title"), "toString should contain the title");
        assertTrue(result.contains("1234"), "toString should contain the ID");
        assertTrue(result.contains("Available") || result.contains("true"), "toString should indicate availability");
        
        String expected = "Title: Title ID: 1234 Available: true";
        assertEquals(expected, result, "toString should match expected format");
    }

    @Test
    @DisplayName("DVD toString method is properly overridden")
    void testDVDToString() {
    
        String result = dvd.toString();
        
        assertNotNull(result, "toString should not return null");
        assertTrue(result.contains("Superbad"), "toString should contain the title");
        assertTrue(result.contains("1001"), "toString should contain the ID");
        assertTrue(result.contains("Available") || result.contains("true"), "toString should indicate availability");
        
        String expected = "Title: Superbad ID: 1001 Available: true";
        assertEquals(expected, result, "toString should match expected format");
    }

    @Test
    @DisplayName("Music toString method is properly overridden")
    void testMusicToString() {
        
        String result = music.toString();
        
        assertNotNull(result, "toString should not return null");
        assertTrue(result.contains("Title"), "toString should contain the title");
        assertTrue(result.contains("1234"), "toString should contain the ID");
        assertTrue(result.contains("Available") || result.contains("true"), "toString should indicate availability");
        
        String expected = "Title: Title ID: 1234 Available: true";
        assertEquals(expected, result, "toString should match expected format");
    }

    @Test
    @DisplayName("Periodical toString method is properly overridden")
    void testPeriodicalToString() {
        
        String result = periodical.toString();
        
        assertNotNull(result, "toString should not return null");
        assertTrue(result.contains("Title"), "toString should contain the title");
        assertTrue(result.contains("1234"), "toString should contain the ID");
        assertTrue(result.contains("Available") || result.contains("true"), "toString should indicate availability");
        assertTrue(result.contains("publisher"), "toString should contain the publisher");
        assertTrue(result.contains("issn"), "toString should contain the ISSN");
        
        // Periodical has a different toString format 
        assertTrue(result.startsWith("Periodical {"), "toString should start with 'Periodical {'");
        assertTrue(result.contains("ID=1234"), "toString should contain the ID");
        assertTrue(result.contains("Title='Title'"), "toString should contain the title");
        assertTrue(result.contains("Publisher='publisher'"), "toString should contain the publisher");
    }

    //----- Field Inheritance Tests -----
    
    @Test
    @DisplayName("All LibraryItems inherit fields from parent class")
    void testFieldInheritance() {

        // Test getters
        assertEquals(1234L, book.getId(), "Book should inherit ID field");
        assertEquals("Title", book.getTitle(), "Book should inherit title field");
        assertNotNull(book.getLocation(), "Book should inherit location field");
        
        assertEquals(1001L, dvd.getId(), "DVD should inherit ID field");
        assertEquals("Superbad", dvd.getTitle(), "DVD should inherit title field");
        assertNotNull(dvd.getLocation(), "DVD should inherit location field");
        
        assertEquals(1234L, music.getId(), "Music should inherit ID field");
        assertEquals("Title", music.getTitle(), "Music should inherit title field");
        assertNotNull(music.getLocation(), "Music should inherit location field");
        
        assertEquals(1234L, periodical.getId(), "Periodical should inherit ID field");
        assertEquals("Title", periodical.getTitle(), "Periodical should inherit title field");
        assertNotNull(periodical.getLocation(), "Periodical should inherit location field");
    }

    @Test
    @DisplayName("All LibraryItems inherit late fee and max borrow days fields")
    void testLateFeeAndBorrowDaysInheritance() {

        // Test that all items have late fees and max borrow days
        assertNotNull(book.getLateFee(), "Book should have late fee field");
        assertTrue(book.getMaxBorrowDays() > 0, "Book should have max borrow days field");
        
        assertNotNull(dvd.getLateFee(), "DVD should have late fee field");
        assertTrue(dvd.getMaxBorrowDays() > 0, "DVD should have max borrow days field");
        
        assertNotNull(music.getLateFee(), "Music should have late fee field");
        assertTrue(music.getMaxBorrowDays() > 0, "Music should have max borrow days field");
        
        assertNotNull(periodical.getLateFee(), "Periodical should have late fee field");
        assertTrue(periodical.getMaxBorrowDays() > 0, "Periodical should have max borrow days field");
    }

    //----- Polymorphism Tests -----
    
    @Test
    @DisplayName("LibraryItems can be treated polymorphically in collections")
    void testPolymorphismWithCollections() {
        
        LibraryItem[] items = {book, dvd, music, periodical};
        
        // Test inherited methods polymorphically
        for (LibraryItem item : items) {
            assertNotNull(item.getItemType(), "Each item should have a type");
            assertTrue(item.isAvailable(), "Each item should be available initially");
            assertTrue(item.getMaxBorrowDays() > 0, "Each item should have max borrow days");
            assertNotNull(item.toString(), "Each item should have toString implementation");
        }
    }

    @Test
    @DisplayName("Polymorphic method calls work correctly")
    void testPolymorphicMethodCalls() {
        LibraryItem[] items = {book, dvd, music, periodical};
        
        // call overridden methods to ensure polymorphic behavior
        for (LibraryItem item : items) {
            String type = item.getItemType(); 
            String toString = item.toString(); 
            int maxDays = item.getMaxBorrowDays(); 
            
            assertNotNull(type, "getItemType should not return null");
            assertNotNull(toString, "toString should not return null");
            assertTrue(maxDays > 0, "getMaxBorrowDays should return positive value");
            
            // Each type 
            switch (type) {
                case "Book":
                    assertEquals(14, maxDays, "Book should have 14 max borrow days");
                    break;
                case "DVD":
                case "Music":
                case "Periodical":
                    assertEquals(7, maxDays, "DVD, Music, and Periodical should have 7 max borrow days");
                    break;
            }
        }
    }

    //----- Abstract Method Implementation Tests -----
    
    @Test
    @DisplayName("Abstract method getItemType is properly implemented by all subclasses")
    void testAbstractMethodImplementation() {
        
        // Verify each subclass provides its own implementation
        assertEquals("Book", book.getItemType(), "Book must implement getItemType");
        assertEquals("DVD", dvd.getItemType(), "DVD must implement getItemType");
        assertEquals("Music", music.getItemType(), "Music must implement getItemType");
        assertEquals("Periodical", periodical.getItemType(), "Periodical must implement getItemType");
    }


    //----- Constructor and Initialization Tests -----
    
    @Test
    @DisplayName("Child class constructors properly initialize inherited fields")
    void testConstructorInheritance() {
        
        // All items should be available by default -> checkIn()
        assertTrue(book.isAvailable(), "Book should be available after construction");
        assertTrue(dvd.isAvailable(), "DVD should be available after construction");
        assertTrue(music.isAvailable(), "Music should be available after construction");
        assertTrue(periodical.isAvailable(), "Periodical should be available after construction");
        
        // Items should have late fees set
        assertTrue(book.getLateFee() > 0, "Book should have late fee set");
        assertTrue(dvd.getLateFee() > 0, "DVD should have late fee set");
        assertTrue(music.getLateFee() > 0, "Music should have late fee set");
        assertTrue(periodical.getLateFee() > 0, "Periodical should have late fee set");
        
        // Items should have max borrow days set
        assertEquals(14, book.getMaxBorrowDays(), "Book should have 14 max borrow days");
        assertEquals(7, dvd.getMaxBorrowDays(), "DVD should have 7 max borrow days");
        assertEquals(7, music.getMaxBorrowDays(), "Music should have 7 max borrow days");
        assertEquals(7, periodical.getMaxBorrowDays(), "Periodical should have 7 max borrow days");
    }    

}

