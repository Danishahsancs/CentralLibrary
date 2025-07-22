package com.zipcodewilmington.centrallibrary;

public class Person {

    private String name; // Name of the person
    private int age; // Age of the person
    private String email; // Email of the person
    private String phoneNumber; // Phone number of the person

    public Person (String name, int age, String email, String phoneNumber) {

        // Constructor to initialize the Person object with name, age, email, and phone number
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // ---- Getters and Setters ----

    // Allow modification of the private fields
    // Edge cases are handled in the setter methods

    // Edge cases include:
    // - Null or empty values
    // - Invalid formats (e.g., email, phone number)
    // - Length constraints (e.g., name, phone number)
    // - Invalid characters (e.g., name, email)
    // - Specific rules (e.g., name must start with a letter, phone number must be digits only)


    public String getName() {

        return name;
    }

    public void setName(String name) {

        //Edge case -> name is null or empty
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        // Edge case -> name is too long
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name cannot be longer than 50 characters");
        }

        // Edge case -> name contains invalid characters
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            throw new IllegalArgumentException("Name can only contain letters and spaces");
        }

        // Edge case -> name must start with a letter
        if (!Character.isLetter(name.charAt(0))) {
            throw new IllegalArgumentException("Name must start with a letter");
        }

        // Edge case -> name must not contain numbers
        if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name must not contain numbers");
        }

        // Edge case -> name must not contain special characters
        //if (!name.matches("^[a-zA-Z\\s]+$")) {
            //throw new IllegalArgumentException("Name must not contain special characters");
        //}   

        // Set the name of the person
        this.name = name;
    }

    public int getAge() {
        
        return age;
    }

    public void setAge(int age) {

        // Edge case -> Minimum age is 7 years old
        if (age < 7) {
            throw new IllegalArgumentException("Age must be at least 7 years old");
        }

        // Edge case -> age is too high
        if (age > 120) {
            throw new IllegalArgumentException("Age cannot be greater than 120");
        }

        // Edge case -> age is not a valid number
        if (age != (int) age) {
            throw new IllegalArgumentException("Age must be a valid number");
        }

        // Set the age of the person
        this.age = age;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        // Edge case -> email is null or empty
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        //Edge case -> email is not valid
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Email is not valid");
        }   

        //Edge case -> must contain @ and .  The edge case called regex check overlaps with this edge case therefore the test fails
      //  if (!email.contains("@") || !email.contains(".")) {
       //     throw new IllegalArgumentException("Email must contain '@' and '.'");
      //  }

        // Set the email of the person
        this.email = email;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        // Edge case -> phone number is null or empty
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        // Edge case -> phone number is not valid
        if (!phoneNumber.matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Phone number is not valid");
        }

        // Edge case -> phone number must contain only digits
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Phone number must contain only digits");
        }

        // Edge case -> phone number must be between 10 and 15 digits
        if (phoneNumber.length() < 10 || phoneNumber.length() > 15) {
            throw new IllegalArgumentException("Phone number must be between 10 and 15 digits");
        }

        // Set the phone number of the person
        this.phoneNumber = phoneNumber;
    }
}
