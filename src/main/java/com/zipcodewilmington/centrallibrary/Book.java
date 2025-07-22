package com.zipcodewilmington.centrallibrary;

public class Book extends LibraryItem 
{
    // Additional Fields from LibraryItem class
    private String author;
    private String isbn;
    

    //Constructors
    public Book(Long id,String title,String location,Double lateFee,int maxBorrowDays,String author,String isbn)
        {
        setId(id);
        setTitle(title);
        setLocations(location);
        setLateFee(lateFee);   
        setMaxBorrowDays(maxBorrowDays);
        this.author = author;
        this.isbn= isbn;
        checkIn();  // set default as True therefore Availabile by defualt

        }   

    // getters
    public String getAuthor() {
        return author;
        }
    public String getIsbn() {
        return isbn;
        }
    //setters
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
    
}
