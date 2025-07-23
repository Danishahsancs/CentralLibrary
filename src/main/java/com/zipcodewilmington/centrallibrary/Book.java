package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;

public class Book extends LibraryItem implements Searchable , Reservable
{
    // Additional Fields from LibraryItem class
    private String author;
    private String isbn;
    private int pages;
    private String  genre;
    private boolean reserved = false; // setting the reserved to to false therefore book is available
   

    //Constructors
    public Book(Long id,String title,Library location,String author,String isbn,int pages,String genre)
        {
        setId(id);
        setTitle(title);
        setLocations(location);
        setLateFee(0.50);   
        setMaxBorrowDays(14);
        this.author = author;
        this.isbn= isbn;
        this.pages = pages;
        this.genre = genre;
        checkIn();  // set default as True therefore Availabile by defualt

        }   

    // getters
    public int getPages()
        {
            return pages;
        }
    public String getGenre()
        {
        return genre;
        }
    public String getAuthor() 
        {
        return author;
        }
    public String getIsbn() {
        return isbn;
        }
    //setters
    public void setPages ( int pages)
        {
            this.pages=pages;
        }
    public void setGenre(String genre)
        {
            this.genre = genre;
        }
    public void setAuthor(String author) 
        {
        this.author = author;
        }

    public void setIsbn(String isbn) 
        {
        this.isbn = isbn;
        }

    // not sure about this
    @Override
        public String getItemType()
        {
            return "Book";
        }
      @Override 
    public String toString() {

        // Return a string representation of the Book (title, id, and availability)
        return "Title: " + getTitle() + " ID: " + getId() + " Available: " + isAvailable();
    }


    //// Search
    @Override
    public List<String> getSearchableFields() {
        List<String> fields = new ArrayList<>();
        fields.add(this.getTitle());
        fields.add(author);
        fields.add(genre);
        fields.add(isbn);
        return fields;
    }
 // Reserve
    @Override
public void reserve(LibraryMember member) 
{
    if (reserved) 
    {
        throw new IllegalStateException("Book is already reserved.");
    }
    this.reserved = true;
}

@Override
public void cancelReservation() 
{
    if (!reserved) 
    {
        throw new IllegalStateException("Book is not reserved.");
    }
    this.reserved = false;
}

@Override
public boolean isReserved() 
{
    return reserved;
}

    // @Override
    // public boolean matchesKeyword(String keyword) {
    //     for (String field : this.getSearchableFields()) {
    //         if (field != null && field.toLowerCase().contains(keyword.toLowerCase())) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
}
