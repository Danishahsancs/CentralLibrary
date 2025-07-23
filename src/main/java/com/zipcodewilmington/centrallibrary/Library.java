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

    @Override
    public String toString() {
        return "Library: " + libraryName + ", Address: " + address;
    }

}
