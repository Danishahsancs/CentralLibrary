package com.zipcodewilmington.centrallibrary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest

{   
 @Test
    public void testConstructors()
    {
        // creating object and assiging values
        Book book= new Book(1234L,"Title","Location","Younis","isbn",256,"Horror");
        assertEquals(1234L,book.getId());
        assertEquals("Title",book.getTitle()); 
        assertEquals("Location",book.getLocation()); 
        assertEquals(0.5,book.getLateFee()); 
        assertEquals(14,book.getMaxBorrowDays()); 
        assertEquals("Younis",book.getAuthor()); 
        assertEquals("isbn",book.getIsbn());
        assertEquals("Horror", book.getGenre());
        assertEquals(256, book.getPages());
        assertTrue(book.isAvailable()) ;
         

    }
    @Test
    public void testSetter()
        {
        Book book= new Book(1234L,"Title","Location","Younis","isbn",255,"Romantic");
        book.setId(1234L);
        book.setTitle("Title");
        book.setLocations("Location");
        book.setAuthor("Younis");
        book.setIsbn("isbn");
        book.setPages(256);
        book.setGenre("Horror");
        

        assertEquals(1234L,book.getId());
        assertEquals("Title",book.getTitle()); 
        assertEquals("Location",book.getLocation()); 
        assertEquals("Younis",book.getAuthor());
        assertEquals("isbn",book.getIsbn()); 
        assertEquals("Horror", book.getGenre());
        assertEquals(256, book.getPages());
        assertTrue(book.isAvailable()) ;
        } 

}
    
    



    
    
        







 






