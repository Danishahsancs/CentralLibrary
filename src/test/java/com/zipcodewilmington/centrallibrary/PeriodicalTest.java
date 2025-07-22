package com.zipcodewilmington.centrallibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PeriodicalTest {

 @Test
    public void testConstructors()
    {
        // creating object and assiging values
        Periodical news= new Periodical(1234L,"Title","Location","publisher","issn","volume","issuenumber","date");
        assertEquals(1234L,news.getId());
        assertEquals("Title",news.getTitle()); 
        assertEquals("Location",news.getLocation()); 
        assertEquals(0.25,news.getLateFee()); 
        assertEquals(7,news.getMaxBorrowDays()); 
        assertEquals("publisher",news.getPublisher()); 
        assertEquals("issn",news.getIssn());
        assertEquals("volume", news.getVolume());
        assertEquals("issuenumber", news.getIssueNumber());
        assertEquals("date", news.getPublicationDate());
      
        assertTrue(news.isAvailable()) ;
         

    }
    @Test
    public void testSetter()
        {
         Periodical news= new Periodical(12345L,"Title1","Location1","publisher1","issn1","volume1","issuenumber1","date1");
        news.setId(1234L);
        news.setTitle("Title");
        news.setLocations("Location");
        news.setPublisher("publisher");
        news.setIssn("issn");
        news.setVolume("volume");
        news.setIssuenumber("issuenumber");
        news.setPublicationDate("date");

        assertEquals(1234L,news.getId());
        assertEquals("Title",news.getTitle()); 
        assertEquals("Location",news.getLocation()); 
        assertEquals(0.25,news.getLateFee()); 
        assertEquals(7,news.getMaxBorrowDays()); 
        assertEquals("publisher",news.getPublisher()); 
        assertEquals("issn",news.getIssn());
        assertEquals("volume", news.getVolume());
        assertEquals("issuenumber", news.getIssueNumber());
        assertEquals("date", news.getPublicationDate());
      
        assertTrue(news.isAvailable()) ;
         
        } 

}

