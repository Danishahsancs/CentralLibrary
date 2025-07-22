package com.zipcodewilmington.centrallibrary;

public class DVD extends LibraryItem {
    
    // Additional Fields from LibraryItem class
    private String director;
    private int duration; // in minutes
    private String rating; // e.g., PG, R, etc.
    private String genre;

    public DVD(Long id, String title, String location, Double lateFee, int maxBorrowDays, String director, int duration, String rating, String genre) {
        
        // Set fields from LibraryItem class
        setId(id);
        setTitle(title);
        setLocations(location);
        setLateFee(lateFee);
        setMaxBorrowDays(maxBorrowDays);
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.genre = genre;
        checkIn(); // set default as True therefore Available by default

    }

    //----- Getters ------

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }
    
    public String getGenre() {
        return genre;
    }

    //---- Setters ------

    public void setDirector(String director) {
        // Edge case -> if director is null or empty, throw an exception
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("Director cannot be null or empty");
        }
        this.director = director;
    }

    public void setDuration(int duration) {
        // Edge case -> if duration is less than or equal to 0, throw an exception
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive integer");
        }
        this.duration = duration;
    }

    public void setRating(String rating) {
        // Edge case -> if rating is null or empty, throw an exception
        if (rating == null || rating.trim().isEmpty()) {
            throw new IllegalArgumentException("Rating cannot be null or empty");
        }
        this.rating = rating;
    }

    public void setGenre(String genre) {
        // Edge case -> if genre is null or empty, throw an exception
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        }
        this.genre = genre;
    }

    // -----LibraryItem methods-----

    @Override
    public String getItemType() {
        // Return the type of item
        return "DVD";
    }

    @Override
    public Double calculateLateFee(int daysCheckedOut) {
        // If the number of days checked out is less than or equal to max borrow days, no late fee
        if (daysCheckedOut <= getMaxBorrowDays()) {
            return 0.0;
        }

        //what is the late fee for DVD?
        return (daysCheckedOut - getMaxBorrowDays()) * getLateFee();
    }

    @Override
    public int getMaxBorrowDays() {
        // Return the maximum borrow days from the LibraryItem class

        // what is the max borrow days for DVD?
        return super.getMaxBorrowDays();
    }

    @Override
    public Double getLateFee() {
        // Return the late fee from the LibraryItem class

        //what is the late fee for DVD?
        return super.getLateFee();
    }

}
