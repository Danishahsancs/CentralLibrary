package com.zipcodewilmington.centrallibrary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DVDTest {

    @Test
    public void testConstructor() {


        // Creating object and assigning values

        DVD dvd = new DVD(1001L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        assertEquals(1001L, dvd.getId());
        assertEquals("Superbad", dvd.getTitle());
        assertEquals("Comedy Section", dvd.getLocation());
        assertEquals(1.00, dvd.getLateFee());
        assertEquals(7, dvd.getMaxBorrowDays());
        assertEquals("Greg Mottola", dvd.getDirector());
        assertEquals(113, dvd.getDuration());
        assertEquals("R", dvd.getRating());
        assertEquals("Comedy", dvd.getGenre());
        assertTrue(dvd.isAvailable());
    }

    @Test
    public void testGetters() {
        
        DVD dvd = new DVD(1002L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        assertEquals("Greg Mottola", dvd.getDirector());
        assertEquals(113, dvd.getDuration());
        assertEquals("R", dvd.getRating());
        assertEquals("Comedy", dvd.getGenre());
    }

    @Test
    public void testSetDirector() {
       
        DVD dvd = new DVD(1001L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        dvd.setDirector("New Director");

        assertEquals("New Director", dvd.getDirector());
    }

    @Test
    public void testSetDirectorWithEmptyStringThrowsException() {
        
        DVD dvd = new DVD(1001L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        assertThrows(IllegalArgumentException.class, () -> {
            dvd.setDirector("");
        });
    }

    @Test
    public void testSetDuration() {
    
        DVD dvd = new DVD(1001L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        dvd.setDuration(180);

        assertEquals(180, dvd.getDuration());
    }

    @Test
    public void testSetDurationWithNegativeValueThrowsException() {

        DVD dvd = new DVD(1001L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        assertThrows(IllegalArgumentException.class, () -> {
            dvd.setDuration(-10);
        });
    }

    @Test
    public void testSetRating() {

        DVD dvd = new DVD(1002L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        dvd.setRating("PG-13");

        assertEquals("PG-13", dvd.getRating());
    }

    @Test
    public void testSetRatingWithEmptyStringThrowsException() {

        DVD dvd = new DVD(1002L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        assertThrows(IllegalArgumentException.class, () -> {
            dvd.setRating("");
        });
    }

    @Test
    public void testSetGenre() {

        DVD dvd = new DVD(1002L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        dvd.setGenre("Drama");

        assertEquals("Drama", dvd.getGenre());
    }

    @Test
    public void testGetItemType() {

        DVD dvd = new DVD(1002L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        String itemType = dvd.getItemType();

        assertEquals("DVD", itemType);
    }

    @Test
    public void testInheritedMethods() {

        DVD dvd = new DVD(1001L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        assertTrue(dvd.isAvailable()); // Should be available by default
        
        dvd.checkOut();
        assertFalse(dvd.isAvailable());
        
        dvd.checkIn();
        assertTrue(dvd.isAvailable());
    }

    @Test
    public void testCalculateLateFeeWithLateFee() {

        DVD dvd = new DVD(1001L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        // (3 days late)
        Double lateFee = dvd.calculateLateFee(10);

        assertEquals(3.0, lateFee); // 3 days * $1.00 per day
    }

    @Test
    public void testCalculateLateFeeUnderMaxDays() {

        DVD dvd = new DVD(1002L, "Superbad", "Comedy Section", "Greg Mottola", 113, "R", "Comedy");

        // 5 days (under the 7 day limit)
        Double lateFee = dvd.calculateLateFee(5);

        assertEquals(0.0, lateFee);
    }
}
