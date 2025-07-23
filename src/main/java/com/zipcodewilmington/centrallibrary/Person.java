package com.zipcodewilmington.centrallibrary;

public class Person {

    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private Address address;

    public Person(String name, int age, String email, String phoneNumber, Address address) {
        setName(name);
        setAge(age);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // ---- Getters and Setters ----

    // Edge cases:
    // - Name cannot be null or empty, must start with a letter, and can only contain letters and spaces.
    // - Age must be between 7 and 120.
    // - Email must be a valid format.
    // - Phone number must be 10 to 15 digits, optionally starting with '+'.

    public String getName() {
        return name;
    }

    public void setName(String name) {

        // Edge case -> if name is null or empty, throw an exception
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        // Edge case -> if name is longer than 50 characters, throw an exception
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name cannot be longer than 50 characters");
        }
        // Edge case -> name must start with a letter and can only contain letters and spaces
        if (!name.matches("^[A-Za-z][A-Za-z\\s]*$")) {
            throw new IllegalArgumentException("Name must start with a letter and contain only letters and spaces");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        // Edge case -> if age is less than 7 or greater than 120, throw an exception
        if (age < 7 || age > 120) {
            throw new IllegalArgumentException("Age must be between 7 and 120");
        }
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        // Edge case -> if email is null or empty, throw an exception
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        // Edge case -> email must be a valid format (basic email regex)
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Email is not valid");
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        // Edge case -> if phone number is null or empty, throw an exception
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        
        this.phoneNumber = phoneNumber;
    }
}
