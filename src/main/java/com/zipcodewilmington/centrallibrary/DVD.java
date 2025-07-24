package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;

public class DVD extends LibraryItem implements Reservable {
    
    // Additional Fields from LibraryItem class
    private String director;
    private int duration; // in minutes
    private String rating; // e.g., PG, R, etc.
    private String genre;
    private boolean reserved = false; // setting the reserved to to false therefore book is available
    private LibraryMember reservee;

    public DVD(Long id, String title, Library location, String director, int duration, String rating, String genre) {
        
        // Set fields from LibraryItem class
        setId(id);
        setTitle(title);
        setLocations(location);
        setLateFee(1.00); // DVDs have 1 dollar late fee
        setMaxBorrowDays(7); // DVDs can be borrowed for 7 days
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.genre = genre;
        checkIn();// set default as True therefore Available by default
        reservee = null;

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

    @Override //May change later
    public String toString() {

        // Return a string representation of the DVD (title, id, and availability)
        return "Title: " + getTitle() + " ID: " + getId() + " Available: " + isAvailable();
    }

    // @Override
    // public boolean matchesKeyword(String keyWord) {
    //     for (String field : this.getSearchableFields()) {
    //         if (field != null && field.toLowerCase().contains(keyWord.toLowerCase())) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    @Override
    public List<String> getSearchableFields() {
        List<String> fields = new ArrayList<>();
        fields.add(this.getTitle());
        fields.add(director);
        fields.add(genre);
        fields.add(String.valueOf(getId()));
        return fields;
    }

    // Reserve
    @Override
public void reserve(LibraryMember member) 
{
     if (reserved) {
            throw new IllegalStateException("Book is already reserved.");
        } else {
            this.reserved = true;
            member.reserveItem(this);
            reservee = member;
        }
}

@Override
public void cancelReservation(LibraryMember member) 
{
    if (!reserved) {
            throw new IllegalStateException("Book is not reserved.");
        } else {
            reservee = null;
            this.reserved = false;
            member.removeReservedItem(this);
        }
}

@Override
public boolean isReserved() 
{
    return reserved;
}
}
