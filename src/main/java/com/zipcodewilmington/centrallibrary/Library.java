package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;

public class Library {
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

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public void addItem(LibraryItem item) {
        items.add(item);
        // System.out.println(libraryName + " added item: '" + item.getTitle() + "' (ID:
        // " + item.getId() + ").");
    }

    public void removeItem(LibraryItem item) {

        if (items.contains(item)) {
            // System.out.println(libraryName + " added item: '" + item.getTitle() + "' (ID:
            // " + item.getId() + ").");
            items.remove(item);
        } else {
            System.out.println(item + " is not in library");
        }
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

    public List<LibraryItem> search(String keyword) {
        List<LibraryItem> resultList = new ArrayList<>();

        for (LibraryItem item : items) {
            if (item.matchesKeyword(keyword)) {
                resultList.add(item);
            }
        }

        return resultList;
    }

    // Prints a list of all items -> item type, title and availability
    public void displayAllItems() {

        // Edge case -> if items is empty, print a message and return
        if (items.isEmpty()) {
            System.out.println("No items currently in the library inventory.");
        return;
    }

    System.out.println("╔══════════════════════════════════════════════════════════════╗");
    System.out.println("║                    LIBRARY INVENTORY                         ║");
    System.out.println("╚══════════════════════════════════════════════════════════════╝");
    System.out.println();
    
    System.out.println("┌─────────────────────────────────────────────────────────────┐");
    System.out.println("│ ID    │ Type        │ Title                    │ Status     │");
    System.out.println("├─────────────────────────────────────────────────────────────┤");


        // Print each item with formatted output
        for (LibraryItem item : items) {
            String itemType = item.getItemType();
            String title = item.getTitle();
            String availability = item.isAvailable() ? "Available" : "Checked out";

            System.out.printf("│ %-5d │ %-10s │ %-25s │ %-10s │%n", item.getId(), itemType, title, availability);

        }
            System.out.println("└─────────────────────────────────────────────────────────────┘");
            System.out.println("Items in " + libraryName + ": " + items.size());
            System.out.println();
            // System.out.println("Total Items: " + items.size());
            // System.out.println("Available: " + availability);
            // System.out.printf("%-12s %-30s %-12s%n", itemType, title, availability);
        }


    public void generateReportItems()
    {
        double total = 0.0;
        for (LibraryMember member : members) 
        {
            // List<LibraryItem> borrowedItems = member.getBorrowedItems();
            // if (!borrowedItems.isEmpty())
            // {
            //     // for ( LibraryItem item : borrowedItems)
            //     // {
            //     //     System.out.println("********************************");
            //     //     System.out.println("Member: " + member.getName());
            //     //    // double fee = item.calculateLateFee(); 
            //     //     System.out.println("  Title: " + item.getTitle());
            //     //     System.out.println("    Max Borrow Days: " + item.getMaxBorrowDays());
            //     //     // System.out.printf("    Late Fee: $%.2f\n", fee);
            //     //     System.out.println("********************************");
            //     // }


            // }
            
            if (member.getOutstandingFees()>0.0)
            {
                System.out.println("********************************");
                 System.out.println("Member: " + member.getName() + "Amount Owed:" + member.getOutstandingFees());
                total = total + member.getOutstandingFees();

                System.out.println("********************************");
                
            }
            
            
        } System.out.println("Amount of money owed to Libarary:"+ total);
    }

    @Override
    public String toString() {
        return "Library: " + libraryName + ", Address: " + address;
    }

}
