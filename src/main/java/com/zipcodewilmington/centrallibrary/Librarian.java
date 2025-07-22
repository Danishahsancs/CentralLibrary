package com.zipcodewilmington.centrallibrary;

public class Librarian  extends Person {
    
    // ---- Attributes ----

    private String employeeId;
    private String department;
    private double salary;

    public Librarian(String name, int age, String email, String phoneNumber, String employeeId, String department, double salary) {
        
        // Call the constructor of Person class to set name, age, email, and phone number
        super(name, age, email, phoneNumber);
        this.employeeId = employeeId;
        this.department = department;
        this.salary = salary;
    }

    // ---- Getters ----

    public String getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    // ---- Setters ----

    public void setEmployeeId(String employeeId) {
        // Edge case -> if employeeId is null or empty, throw an exception
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
        this.employeeId = employeeId;
    }

    public void setDepartment(String department) {
        // Edge case -> if department is null or empty, throw an exception
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty");
        }
        this.department = department;
    }

    public void setSalary(double salary) {
        // Edge case -> if salary is negative, throw an exception
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    // ---- Methods ----
    
    public void addItemToLibrary(Library library, LibraryItem item) {
        
       // Edge cases -> if item is null or library is null, throw an exception
        if (item == null) {
            throw new IllegalArgumentException("Error: Cannot add null item to the library.");
        } else if (library == null) {
            throw new IllegalArgumentException("Error: Library reference is null.");
        } else {
            library.addItem(item);

        // Log the addition of the item to the library
        System.out.println(getName() + " (Librarian ID: " + employeeId + ") added item: '" + item.getTitle() + "' (ID: " + item.getId() + ").");
        }

    }
    
    public void removeItemFromLibrary(Library library, LibraryItem item) {
        
        // Edge cases -> if item is null or library is null, throw an exception
        if (item == null) {
            throw new IllegalArgumentException("Error: Cannot remove null item from the library.");
        } else if (library == null) {
            throw new IllegalArgumentException("Error: Library reference is null.");
        } else {
            library.removeItem(item);

        // Log the removal of the item from the library
        System.out.println(getName() + " (Librarian ID: " + employeeId + ") removed item: '" + item.getTitle() + "' (ID: " + item.getId() + ").");
        }
    }

}
