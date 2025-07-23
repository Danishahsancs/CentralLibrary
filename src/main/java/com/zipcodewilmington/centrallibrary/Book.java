package com.zipcodewilmington.centrallibrary;

public class Book extends LibraryItem 
{
    // Additional Fields from LibraryItem class
    private String author;
    private String isbn;
    private int pages;
    private String  genre;
    

    //Constructors
    public Book(Long id,String title,String location,String author,String isbn,int pages,String genre)
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
    
}
