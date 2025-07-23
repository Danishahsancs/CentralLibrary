package com.zipcodewilmington.centrallibrary;

public abstract class LibraryItem implements Searchable{
    private Long id;
    private String title;

    // maybe have this as address object
    private Library location;
    private Boolean availability;
    private Double lateFee;
    private int maxBorrowDays;

    public Library getLocation() {
        return location;
    }

    public void setLocations(Library location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLateFee() {
        return lateFee;
    }

    public void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }

    public int getMaxBorrowDays() {
        return maxBorrowDays;
    }

    public void setMaxBorrowDays(int maxBorrowDays) {
        this.maxBorrowDays = maxBorrowDays;
    }

    public Boolean isAvailable() {
        return availability;
    }

    public void checkOut() {
        availability = false;
    }

    public void checkIn() {
        availability = true;
    }

    public Double calculateLateFee(int daysCheckedOut) {
        if (daysCheckedOut <= maxBorrowDays)
            return 0.0;
        return (daysCheckedOut - maxBorrowDays) * lateFee;
    }

    public abstract String getItemType();
}
