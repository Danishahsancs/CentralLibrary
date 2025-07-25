package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember extends Person {
    private long memberId;
    private String membershipDate;
    private List<LibraryItem> borrowedItems;
    private List<LibraryItem> reservedItem;
    private Double outstandingFees;
    

    public LibraryMember(String name, int age, String email, String phoneNumber, long memberId, String membershipDate, Address address) {
        super(name, age, email, phoneNumber,address);
        this.memberId = memberId;
        this.membershipDate = membershipDate;
        this.borrowedItems = new ArrayList<>();
        this.reservedItem = new ArrayList<>();
        this.outstandingFees = 0.0;
    }

    public void borrowItem(LibraryItem item) {
        if (item.isAvailable()) {
            item.checkOut();
            borrowedItems.add(item);
        } else {
            System.out.println("Item is not available.");
        }
    }

    public void reserveItem(LibraryItem item) {
        if (item.isAvailable()) {
           reservedItem.add(item);
        } else {
            System.out.println("Item is not available to reserve.");
        }
    }

    public void removeReservedItem(LibraryItem item){
        reservedItem.remove(item);
    }

    public void returnItem(LibraryItem item, int daysLate) {
        if (borrowedItems.contains(item)) {
            item.checkIn();
            borrowedItems.remove(item);
            // Calculate total days checked out: max borrow days + days late
            int totalDaysCheckedOut = item.getMaxBorrowDays() + daysLate;
            double lateFee = item.calculateLateFee(totalDaysCheckedOut);
            outstandingFees += lateFee;
        }
    }

    public void payFees(double amount) {
        if (amount > 0) {
            outstandingFees -= amount;
            if (outstandingFees < 0) {
                outstandingFees = 0.0;
            }
        }
    }

    public List<LibraryItem> getReservedItems(){
        return reservedItem;
    }
    public long getMemberId() {
        return memberId;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    public Double getOutstandingFees() {
        return outstandingFees;
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "\nname=" + this.getName() +
                "\nmemberId=" + memberId +
                "\nmembership Date='" + membershipDate + '\'' +
                "\nborrowedI tems=" + borrowedItems.size() +
                "\nreserved items=" + reservedItem.size() +
                "\noutstanding Fees=" + outstandingFees +
                "\naddress=" +this.getAddress()+
                "\n}";
    }
}
