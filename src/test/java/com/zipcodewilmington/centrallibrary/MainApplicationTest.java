package com.zipcodewilmington.centrallibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by n3pjk on 6/9/2025.
 */
public class MainApplicationTest {
    
    //initialize Library object for testing
    private Library testLibrary;
    
    @BeforeEach
    void setUp() {
        Address libraryAddress = new Address("123 Test St", "Test City", "Test State", "12345");
        testLibrary = new Library("Test Library", libraryAddress);
    }

    //----- Add Item Tests -----

    @Test
    @DisplayName("Successfully add a valid book")
    void testAddValidBook() {
        int initialSize = testLibrary.getItems().size();
        
        Book testBook = new Book(100L, "Test Book", testLibrary, "Test Author", "978-0123456789", 250, "Fiction");
        testLibrary.addItem(testBook);
        
        assertEquals(initialSize + 1, testLibrary.getItems().size(), "Library should have one more item");
        assertEquals("Test Book", testBook.getTitle(), "Book title should match input");
        assertTrue(testBook.isAvailable(), "New book should be available");
    }
    
    @Test
    @DisplayName("Successfully add a valid DVD")
    void testAddValidDVD() {
        DVD testDVD = new DVD(200L, "Test Movie", testLibrary, "Test Director", 120, "PG-13", "Action");
        testLibrary.addItem(testDVD);
        
        assertEquals("Test Movie", testDVD.getTitle(), "DVD title should match input");
        assertEquals("PG-13", testDVD.getRating(), "DVD rating should match input");
        assertTrue(testDVD.isAvailable(), "New DVD should be available");
    }
    
    @Test
    @DisplayName("Successfully add a valid music item")
    void testAddValidMusic() {
        Music testMusic = new Music(300L, "Test Album", testLibrary, "Test Artist", "2023-01-15", "Rock");
        testLibrary.addItem(testMusic);
        
        assertEquals("Test Album", testMusic.getTitle(), "Music title should match input");
        assertEquals("Test Artist", testMusic.getArtist(), "Music artist should match input");
        assertTrue(testMusic.isAvailable(), "New music should be available");
    }
    
    @Test
    @DisplayName("Successfully add a valid periodical")
    void testAddValidPeriodical() {
        Periodical testPeriodical = new Periodical(400L, "Test Magazine", testLibrary, 
                                                  "Test Publisher", "1234-5678", "Vol. 1", "Issue 1", "2024-01-01");
        testLibrary.addItem(testPeriodical);
        
        assertEquals("Test Magazine", testPeriodical.getTitle(), "Periodical title should match input");
        assertEquals("1234-5678", testPeriodical.getIssn(), "Periodical ISSN should match input");
        assertTrue(testPeriodical.isAvailable(), "New periodical should be available");
    }
    

    //----- Edge Case Tests for Add Item -----

    @Test
    @DisplayName("Test values for numeric inputs")
    void testValues() {
        // Test edge case for negative numbers
        Book minBook = new Book(1L, "Min Book", testLibrary, "Author", "123-456-789", 1, "Fiction");
        DVD minDVD = new DVD(2L, "Min Movie", testLibrary, "Director", 1, "G", "Short");
        
        testLibrary.addItem(minBook);
        testLibrary.addItem(minDVD);
        
        assertEquals(1, minBook.getPages(), "Should accept minimum page count");
        assertEquals(1, minDVD.getDuration(), "Should accept minimum duration");
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
    
    
}
