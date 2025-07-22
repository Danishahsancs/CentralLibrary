package com.zipcodewilmington.centrallibrary;

public class Periodical extends LibraryItem {
    // Additional Fields from LibraryItem class
    private String publisher;
    private String issn;
    private String volume;
    private String  issuenumber;
    private String publicationdate;

    

    //Constructors
    public Periodical(Long id,String title,String location,String publisher,String issn,String volume,String  issuenumber,String publicationdate)
        {
        setId(id);
        setTitle(title);
        setLocations(location);
        setLateFee(0.25);   
        setMaxBorrowDays(7);
        this.publisher = publisher;
        this.issn= issn;
        this.volume = volume;
        this.issuenumber = issuenumber;
        this.publicationdate = publicationdate;
        checkIn();  // set default as True therefore Availabile by defualt

        }   

    // getters
    public String getPublisher() 
        {
        return publisher;
        }
        public String getIssn() 
        {
        return issn;
        }
    public String getVolume()
        {
            return volume;
        }
    public String getIssueNumber()
        {
        return issuenumber;
        }
    public String getPublicationDate()
        {
        return publicationdate;
        }
    
    
    
    //setters
    
      public void setPublisher(String publisher) 
        {
        this.publisher = publisher;
        }
    public void setIssn (String issn) 
        {
        this.issn = issn;
        }
     public void setVolume(String volume)
        {
            this.volume = volume;
        }

    public void setIssuenumber ( String issuenumber)
        {
            this.issuenumber = issuenumber;
        }

    public void setPublicationDate ( String publicationdate)
        {
            this.publicationdate = publicationdate;
        }
    
   
  
  

    // not sure about this
    @Override
        public String getItemType()
        {
            return "Periodical";
        }
    
}
