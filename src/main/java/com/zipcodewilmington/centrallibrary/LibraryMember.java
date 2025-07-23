package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private Person person;
    private long memberId;
    private String membershipDate;
    private List<LibraryItem> borrowedItems;
    private Double outStandingFees;
    private Address address;

    public LibraryMember(Person person, long memberId, String membershipDate, Address address) {
        this.person = person;
        this.memberId = memberId;
        this.membershipDate = membershipDate;
        this.address = address;
        this.borrowedItems = new ArrayList<>();
        this.outStandingFees = 0.0;
    }

    public void borrowItem(LibraryItem item) {
        if (item.isAvailable()) {
            item.checkOut();
            borrowedItems.add(item);
        } else {
            System.out.println("Item is not available.");
        }
    }

    public void returnItem(LibraryItem item, int daysLate) {
        if (borrowedItems.contains(item)) {
            item.checkIn();
            borrowedItems.remove(item);
            double lateFee = item.calculateLateFee(daysLate);
            outStandingFees += lateFee;
        }
    }

    public void payFees(double amount) {
        if (amount > 0) {
            outStandingFees -= amount;
            if (outStandingFees < 0) {
                outStandingFees = 0.0;
            }
        }
    }

    public Person getPerson() {
        return person;
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

    public Double getOutStandingFees() {
        return outStandingFees;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "\nname=" + person.getName() +
                "\nmemberId=" + memberId +
                "\nmembershipDate='" + membershipDate + '\'' +
                "\nborrowedItems=" + borrowedItems.size() +
                "\noutStandingFees=" + outStandingFees +
                "\naddress=" + address +
                "\n}";
    }
}
