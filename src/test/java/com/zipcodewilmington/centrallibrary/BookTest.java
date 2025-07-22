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
        Book book= new Book(1234L,"Title","Location",20.00,14,"Younis","isbn");
        assertEquals(1234L,book.getId());
        assertEquals("Title",book.getTitle()); 
        assertEquals("Location",book.getLocation()); 
        assertEquals(20.00,book.getLateFee()); 
        assertEquals(14,book.getMaxBorrowDays()); 
        assertEquals("Younis",book.getAuthor()); 
        assertEquals("isbn",book.getIsbn());
        assertTrue(book.isAvailable()) ;
         

    }
    @Test
    public void testSetter()
    {   Book book= new Book(12345L,"Title1","Location1",21.00,15,"Younis1","isb2n");
        book.setId(1234L);
        book.setTitle("Title");
        book.setLocations("Location");
        book.setLateFee(20.00);
        book.setMaxBorrowDays(14);
        book.setAuthor("Younis");
        book.setIsbn("isbn");
        

        assertEquals(1234L,book.getId());
        assertEquals("Title",book.getTitle()); 
        assertEquals("Location",book.getLocation()); 
        assertEquals(20.00,book.getLateFee()); 
        assertEquals(14,book.getMaxBorrowDays()); 
        assertEquals("Younis",book.getAuthor());
        assertEquals("isbn",book.getIsbn()); 
        assertTrue(book.isAvailable()) ;
         

    }
    
    



    
    
        







}
    






