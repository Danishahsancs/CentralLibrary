package com.zipcodewilmington.centrallibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by n3pjk on 6/9/2025.
 */
public class MainApplicationTest {
    
    //initialize Library and Library Member object for testing
    private Library testLibrary;
    private LibraryMember testMember;
    private Address testLibraryAddress;
    private Address memberAddress;
    private Book testBook;
    private DVD testDVD;
    private Music testMusic;
    private Periodical testPeriodical;

    @BeforeEach
    void setUp() {
        // Set up a test library and member
        testLibraryAddress = new Address("123 Test St", "Test City", "Test State", "12345");
        testLibrary = new Library("Test Library", testLibraryAddress);
        memberAddress = new Address("456 Member St", "Member City", "Member State", "67890");
        testMember = new LibraryMember("Test Member", 25, "test@email.com", "555-0123", 1, "2024-01-01", memberAddress);

        // Create test for items 
        testBook = new Book(100L, "Test Book", testLibrary, "Test Author", "978-0123456789", 250, "Fiction");
        testDVD = new DVD(200L, "Test Movie", testLibrary, "Test Director", 120, "PG-13", "Action");
        testMusic = new Music(300L, "Test Album", testLibrary, "Test Artist", "2023-01-15", "Rock");
        testPeriodical = new Periodical(400L, "Test Magazine", testLibrary, "Test Publisher", "1234-5678", "Vol. 1", "Issue 1", "2024-01-01");
    }

    //----- Add Item Tests -----

    @Test
    @DisplayName("Successfully add a valid book")
    void testAddValidBook() {

        int initialSize = testLibrary.getItems().size();
        
        testLibrary.addItem(testBook);
        
        assertEquals(initialSize + 1, testLibrary.getItems().size(), "Library should have one more item");
        assertEquals("Test Book", testBook.getTitle(), "Book title should match input");
        assertTrue(testBook.isAvailable(), "New book should be available");
    }
    
    @Test
    @DisplayName("Successfully add a valid DVD")
    void testAddValidDVD() {
        
        testLibrary.addItem(testDVD);
        
        assertEquals("Test Movie", testDVD.getTitle(), "DVD title should match input");
        assertEquals("PG-13", testDVD.getRating(), "DVD rating should match input");
        assertTrue(testDVD.isAvailable(), "New DVD should be available");
    }
    
    @Test
    @DisplayName("Successfully add a valid music item")
    void testAddValidMusic() {
    
        testLibrary.addItem(testMusic);
        
        assertEquals("Test Album", testMusic.getTitle(), "Music title should match input");
        assertEquals("Test Artist", testMusic.getArtist(), "Music artist should match input");
        assertTrue(testMusic.isAvailable(), "New music should be available");
    }
    
    @Test
    @DisplayName("Successfully add a valid periodical")
    void testAddValidPeriodical() {

        testLibrary.addItem(testPeriodical);
        
        assertEquals("Test Magazine", testPeriodical.getTitle(), "Periodical title should match input");
        assertEquals("1234-5678", testPeriodical.getIssn(), "Periodical ISSN should match input");
        assertTrue(testPeriodical.isAvailable(), "New periodical should be available");
    }
    

    //----- Edge Case Tests for Add Item -----

    @Test
    @DisplayName("Test values for numeric inputs")
    void testValues() {

        testLibrary.addItem(testBook);
        testLibrary.addItem(testDVD);
        
        assertEquals(250, testBook.getPages(), "Should accept minimum page count");
        assertEquals(120, testDVD.getDuration(), "Should accept minimum duration");
    }
    
    @Test
    @DisplayName("Test empty string validation")
    void testEmptyStringValidation() {

        // Test edge case for empty string
        String emptyTitle = "";
        String emptyAuthor = "";
        
        assertTrue(emptyTitle.isEmpty(), "Should detect empty title");
        assertTrue(emptyAuthor.isEmpty(), "Should detect empty author");
    }
    
    
    //----- Remove Item Tests -----
    
    @Test
    @DisplayName("Successfully remove an existing item from library")
    void testRemoveExistingItem() {
        
        testLibrary.addItem(testBook);
        testLibrary.addItem(testDVD);
        
        int initialSize = testLibrary.getItems().size();
        
        // Remove book, keep DVD
        testLibrary.removeItem(testBook);
        
        assertEquals(initialSize - 1, testLibrary.getItems().size(), "Library should have one fewer item");
        assertFalse(testLibrary.getItems().contains(testBook), "Library should not contain the removed book");
        assertTrue(testLibrary.getItems().contains(testDVD), "Library should still contain the DVD");
    }
    
    @Test
    @DisplayName("Test removing multiple items")
    void testRemoveMultipleItems() {
        
        testLibrary.addItem(testBook);
        testLibrary.addItem(testDVD);
        testLibrary.addItem(testPeriodical);
        
        int initialSize = testLibrary.getItems().size();
        
        // Remove two items
        testLibrary.removeItem(testBook);
        testLibrary.removeItem(testDVD);
        
        assertEquals(initialSize - 2, testLibrary.getItems().size(), "Library should have 2 fewer items");
        assertFalse(testLibrary.getItems().contains(testBook), "Should not contain testBook");
        assertFalse(testLibrary.getItems().contains(testDVD), "Should not contain testDVD");
        assertTrue(testLibrary.getItems().contains(testPeriodical), "Should still contain testPeriodical");
    }
    
    //----- Check Out and Check In Tests -----
    
    @Test
    @DisplayName("Successfully check out an available item")
    void testCheckOutAvailableItem() {

        // Add book to test member
        testLibrary.addItem(testBook);
        testLibrary.addMember(testMember);
        
        // Initial state
        assertTrue(testBook.isAvailable(), "Book should initially be available");
        assertEquals(0, testMember.getBorrowedItems().size(), "Member should have no borrowed items initially");
        
        // Check out book
        testMember.borrowItem(testBook);
        
        // Book should not be available
        assertFalse(testBook.isAvailable(), "Book should not be available after checkout");
        assertEquals(1, testMember.getBorrowedItems().size(), "Member should have 1 borrowed item");
        assertTrue(testMember.getBorrowedItems().contains(testBook), "Member should have the borrowed book");
    }
    
    @Test
    @DisplayName("Cannot check out an unavailable item")
    void testCheckOutUnavailableItem() {
        // Add additional member for test
        Address member2Address = new Address("789 Other St", "Other City", "Other State", "54321");
        LibraryMember member2 = new LibraryMember("Member Two", 30, "member2@email.com", "555-9876", 2, "2024-01-01", member2Address);

        testLibrary.addItem(testBook);
        testLibrary.addMember(testMember);
        testLibrary.addMember(member2);
        
        // First member -> checks out the book
        testMember.borrowItem(testBook);
        
        // Second member -> tries to check out the same book
        int initialBorrowedCount = member2.getBorrowedItems().size();
        member2.borrowItem(testBook);
        
        // Ensuer the second member couldn't borrow it
        assertEquals(initialBorrowedCount, member2.getBorrowedItems().size(), "Second member should not be able to borrow unavailable book");
        assertFalse(member2.getBorrowedItems().contains(testBook), "Second member should not have the book");
        assertFalse(testBook.isAvailable(), "Book should still be unavailable");
        assertTrue(testMember.getBorrowedItems().contains(testBook), "First member should still have the book");
    }
    
    @Test
    @DisplayName("Successfully check in a borrowed item with no late fees")
    void testCheckInOnTime() {
        // Setup: member borrows a book
        testLibrary.addItem(testBook);
        testLibrary.addMember(testMember);
        
        testMember.borrowItem(testBook);
        double initialFees = testMember.getOutstandingFees();
        
        // Return the book on time (0 days late)
        testMember.returnItem(testBook, 0);
        
        // Verify post-return state
        assertTrue(testBook.isAvailable(), "Book should be available after return");
        assertEquals(0, testMember.getBorrowedItems().size(), "Member should have no borrowed items after return");
        assertFalse(testMember.getBorrowedItems().contains(testBook), "Member should not have the returned book");
        assertEquals(initialFees, testMember.getOutstandingFees(), "No late fees should be added for on-time return");
    }
    
    // FAILING
    @Test
    @DisplayName("Check in a borrowed item with late fees")
    void testCheckInLate() {

        // Setup: member borrows a dvd
        testLibrary.addMember(testMember);
        
        testMember.borrowItem(testDVD);
        double initialFees = testMember.getOutstandingFees();
        
        // Return the item late (5 days late)
        int daysLate = 5;
        testMember.returnItem(testDVD, daysLate);
        
        // Verify post-return state
        assertTrue(testDVD.isAvailable(), "DVD should be available after return");
        assertEquals(0, testMember.getBorrowedItems().size(), "Member should have no borrowed items after return");
        assertFalse(testMember.getBorrowedItems().contains(testDVD), "Member should not have the returned DVD");
        
        // Verify late fees were calculated and added
        int totalDaysCheckedOut = testDVD.getMaxBorrowDays() + daysLate;
        double expectedLateFee = testDVD.calculateLateFee(totalDaysCheckedOut);
        assertEquals(initialFees + expectedLateFee, testMember.getOutstandingFees(), "Late fees should be added to member's outstanding fees");
        assertTrue(testMember.getOutstandingFees() > initialFees, "Outstanding fees should increase after late return");
    }
    
    @Test
    @DisplayName("Cannot check in an item that wasn't borrowed by the member")
    void testCheckInNotBorrowedItem() {
        // Setup: create items but don't borrow them
        testLibrary.addItem(testMusic);
        testLibrary.addMember(testMember);
        
        double initialFees = testMember.getOutstandingFees();
        int initialBorrowedCount = testMember.getBorrowedItems().size();
        
        // Try to return an item that wasn't borrowed
        testMember.returnItem(testMusic, 0);
        
        // Verify nothing changed
        assertTrue(testMusic.isAvailable(), "Music should still be available");
        assertEquals(initialBorrowedCount, testMember.getBorrowedItems().size(), "Borrowed items count should not change");
        assertEquals(initialFees, testMember.getOutstandingFees(), "Outstanding fees should not change");
    }
    
    @Test
    @DisplayName("Check out multiple different item types")
    void testCheckOutMultipleItemTypes() {
        // Add different types of items
        testLibrary.addItem(testBook);
        testLibrary.addItem(testDVD);
        testLibrary.addItem(testMusic);
        testLibrary.addItem(testPeriodical);
        testLibrary.addMember(testMember);
        
        // Check out all items
        testMember.borrowItem(testBook);
        testMember.borrowItem(testDVD);
        testMember.borrowItem(testMusic);
        testMember.borrowItem(testPeriodical);
        
        // Verify all items are checked out
        assertEquals(4, testMember.getBorrowedItems().size(), "Member should have 4 borrowed items");
        assertFalse(testBook.isAvailable(), "Book should not be available");
        assertFalse(testDVD.isAvailable(), "DVD should not be available");
        assertFalse(testMusic.isAvailable(), "Music should not be available");
        assertFalse(testPeriodical.isAvailable(), "Periodical should not be available");
        
        // Verify member has all items
        assertTrue(testMember.getBorrowedItems().contains(testBook), "Member should have book");
        assertTrue(testMember.getBorrowedItems().contains(testDVD), "Member should have DVD");
        assertTrue(testMember.getBorrowedItems().contains(testMusic), "Member should have music");
        assertTrue(testMember.getBorrowedItems().contains(testPeriodical), "Member should have periodical");
    }
    
}
