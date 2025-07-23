package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;

public class Periodical extends LibraryItem implements Searchable {
    // Additional Fields from LibraryItem class
    private String publisher;
    private String issn;
    private String volume;
    private String issuenumber;
    private String publicationdate;

    // Constructors
    public Periodical(Long id, String title, Library location, String publisher, String issn, String volume,
            String issuenumber, String publicationdate) {
        setId(id);
        setTitle(title);
        setLocations(location);
        setLateFee(0.25);
        setMaxBorrowDays(7);
        this.publisher = publisher;
        this.issn = issn;
        this.volume = volume;
        this.issuenumber = issuenumber;
        this.publicationdate = publicationdate;
        checkIn(); // set default as True therefore Availabile by defualt

    }

    // getters
    public String getPublisher() {
        return publisher;
    }

    public String getIssn() {
        return issn;
    }

    public String getVolume() {
        return volume;
    }

    public String getIssueNumber() {
        return issuenumber;
    }

    public String getPublicationDate() {
        return publicationdate;
    }

    // setters

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setIssuenumber(String issuenumber) {
        this.issuenumber = issuenumber;
    }

    public void setPublicationDate(String publicationdate) {
        this.publicationdate = publicationdate;
    }

    // not sure about this
    @Override
    public String getItemType() {
        return "Periodical";
    }

    @Override
    public String toString() {
        return "Periodical {" +
                "ID=" + getId() +
                ", Title='" + getTitle() + '\'' +
                ", Publisher='" + publisher + '\'' +
                ", IssueNumber='" + issuenumber + '\'' +
                ", PublicationDate='" + publicationdate + '\'' +
                ", ISSN= " + issn + '\''+
                 " Available: " + isAvailable()+
                '}';
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
        fields.add(publisher);
        fields.add(issn);
        return fields;
    }
}
