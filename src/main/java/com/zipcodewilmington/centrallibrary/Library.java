package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;

public class Library{
    private String libraryName;
    Address address;
    private List<LibraryItem> items;
    private List<LibraryMember> members;
    private List<Librarian> librarians;

    public Library(String libraryName, Address address) {
        this.libraryName = libraryName;
        this.address = address;
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.librarians = new ArrayList<>();
    }

    public List<LibraryItem> getItems() {
        return items;
    }

    public List<LibraryMember> getMembers() {
        return members;
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void removeItem(LibraryItem item) {
        items.remove(item);
    }

    public void addMember(LibraryMember member) {
        members.add(member);
    }

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public Address getAddress() {
        return address;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public List<LibraryItem> search(String keyword){
        List<LibraryItem> resultList = new ArrayList<>();
        
        for(LibraryItem item : items){
            if(item.matchesKeyword(keyword)){
                resultList.add(item);
            }
        }

        return resultList;
    }

    public void generateReportItems()
    {
        for (LibraryMember member : members) 
        {
            List<LibraryItem> borrowedItems = member.getBorrowedItems();
            if (!borrowedItems.isEmpty())
            {
                for ( LibraryItem item : borrowedItems)
                {
                    System.out.println("********************************");
                    System.out.println("Member: " + member.getName());
                   // double fee = item.calculateLateFee(); 
                    System.out.println("  Title: " + item.getTitle());
                    System.out.println("    Max Borrow Days: " + item.getMaxBorrowDays());
                    // System.out.printf("    Late Fee: $%.2f\n", fee);
                    System.out.println("********************************");
                }
            }
            
            
        }
    }

    @Override
    public String toString() {
        return "Library: " + libraryName + ", Address: " + address;
    }

}
