package com.zipcodewilmington.centrallibrary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicTest {

    @Test
    public void testConstructors() {
        // creating object and assiging values
        Music music = new Music(1234L, "Title", new Library("Library", null), "Younis", "1995", "Pop");
        assertEquals(1234L, music.getId());
        assertEquals("Title", music.getTitle());
        assertEquals("Library", music.getLocation().toString());
        assertEquals(1.00, music.getLateFee());
        assertEquals(7, music.getMaxBorrowDays());
        assertEquals("Younis", music.getArtist());
        assertEquals("1995", music.getReleaseDate());
        assertEquals("Pop", music.getGenre());
        assertTrue(music.isAvailable());

    }

    @Test
    public void testSetter() {
        Music music = new Music(123L, "Titlex", new Library("Library", null), "Younisx", "1995x", "Popx");
        music.setId(1234L);
        music.setTitle("Title");
        music.setLocations(new Library("Library", null));
        music.setArtist("Younis");
        music.setReleaseDate("1995");
        music.setGenre("Pop");

        assertEquals(1234L, music.getId());
        assertEquals("Title", music.getTitle());
        assertEquals("Library", music.getLocation().toString());
        assertEquals(1.00, music.getLateFee());
        assertEquals(7, music.getMaxBorrowDays());
        assertEquals("Younis", music.getArtist());
        assertEquals("1995", music.getReleaseDate());
        assertEquals("Pop", music.getGenre());
        assertTrue(music.isAvailable());
    }

}
