package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;

public class Music extends LibraryItem implements Searchable
{
    // Additional Fields from LibraryItem class
    private String artist;

    private String releasedate;
    private String genre;
    

    //Constructors
    public Music(Long id,String title,Library location,String artist,String releasedate,String genre)
        {
        setId(id);
        setTitle(title);
        setLocations(location);
        setLateFee(1.00);  // set same as DVD
        setMaxBorrowDays(7); // set same as DVD
        this.artist = artist;
        this.releasedate = releasedate;
        this.genre = genre;
        checkIn();  // set default as True therefore Availabile by defualt

        }   

    // getters
    public String getArtist()
        {
            return artist;
        }
    public String getGenre()
        {
        return genre;
        }
    public String getReleaseDate() {
        return releasedate;
        }
    //setters
    public void setArtist ( String artist)
        {
            this.artist=artist;
        }
    public void setGenre(String genre)
        {
            this.genre = genre;
        }

    public void setReleaseDate(String releasedate) 
        {
        this.releasedate = releasedate;
        }

    @Override
        public String getItemType()
        {
            return "Music";
        }
      @Override 
    public String toString() 
    {
        // Return a string representation of the Music (title, id, and availability)
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
        fields.add(artist);
        fields.add(genre);
        return fields;
    }
    }
