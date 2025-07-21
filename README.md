# Shhhh! Central Library Management System

As a team, fork this repository to an Organization and submit the URL of your fork via the Student Portal. Each teammate will submit the **SAME URL.** 

## Project Overview

Build a comprehensive Library Management System that demonstrates encapsulation, abstraction, inheritance, and polymorphism through a real-world application.

## Learning Objectives

### Design

* To demonstrate [the four pillars of OOP](https://www.geeksforgeeks.org/four-main-object-oriented-programming-concepts-of-java/)
	* Master encapsulation through proper data hiding and access control
	* Implement abstraction using abstract classes and interfaces
	* Apply inheritance to create class hierarchies
	* Demonstrate polymorphism through method overriding and dynamic binding

### Implementation

* Use JUnit to develop useful test modules
* Document components
* Comment code

## Setting the Scene...

Consider a system in which

* `Address` has a `street`, `city`, `state`, and `zipCode`.
* `Searchable` item `matchesKeyword` by searching `getSearchableFields` to if any contain a defined String.
* `Reservable` item can be `reserve` or `cancelReserve` by a `LibraryMember`.
depending on their `isReserved` [flag](https://en.wikipedia.org/wiki/Boolean_flag).
* `LibraryItem` is `Searchable`, and has an `id`, `title`, `location` and availability based on its `isAvailable` [flag](https://en.wikipedia.org/wiki/Boolean_flag). `LibraryItem` can be `checkOut` and `checkIn`, which sets their `isAvailable` flag. `LibraryItem` must be able to `calculateLateFee`, `getMaxBorrowDays` and `getItemType`.
	* `Book` is a `LibraryItem` that has `author`, `isbn`, number of `pages` and `genre`. The `SearchableFields` of `Book` are `title`, `author`, `genre` and `isbn`. `Book` is `Reservable`, and has a late fee of $0.50 per day, can be borrowed for 14 days, and is type `Book`.
	* `Periodical` is a `LibraryItem` that has `publisher`, `issn`, `volume`, `issueNumber` and `publicationDate`. `SearchableFields` of `Periodical` are `title`, `publisher` and `issn`. `Periodical` has a late fee of $0.25 per day, can be borrowed for 7 days, and is type `Periodical`.
	* `DVD` is a `LibraryItem` that has `director`, minutes of `duration`, `rating`, and `genre`. The `SearchableFields` of `DVD` are `title`, `director` and `genre`. `DVD` is `Reservable`, and has a late fee of $1.00 per day, can be borrowed for 7 days, and is type `DVD`.
* `Person` has a `name`, `age`, `email`, and `phonenumber`.
	* `Librarian` is a `Person` that has `employeeId`, `department` and `salary`. A `Librarian` can `addItemToLibrary` and `removeItemFromLibrary`.
	* `LibraryMember` is a `Person` that has `memberId`, `membershipDate`, `List` of `borrowedItems` which are `LibraryItems`, `outstandingFees`, and `address`. `LibraryMember` can `borrowItem`, which `checkOut` item and adds item to `borrowedItems`; can `returnItem`, which `checkIn` item, removes item from `borrowedItems`, and `calcuateLateFee` based on item and `daysLate`; and can `payFees`, which reduces `outstandingFees` by `amount`.
* `Library` has `name`, `address`, `items` is a `List` of `LibraryItem`, `members` is a `List` of `LibraryMember`, `librarians` is a `List` of `Librarian`. `Library` can `addItem`, `removeItem`, which adds or removes `LibraryItem` to or from `items`;`addMember`, which adds `LibraryMember` to `members`; `addLibrarian`, which adds `Librarian` to `librarians`; and `search`, which searches `items` for a String `keyword`; `displayAllItems` lists the item type, title and availability; `generateLateFeeReport` lists the member name, and their list of `borrowedItems`, including `title`, `maxBorrowDays` and `calculateLateFee`.

## Establishing The Plot

* `Hypatia`, is the `Librarian` of `Central Library`, at `123 Main St`, `Alexandra`, `DE` `12345`. Her `employeeId` is `L001`, and she makes $`45000` a year. The `Library` has
	* 2 `Book`.
		* `B001` is `Java Programming` by `John Doe`, 500 pages, located in `A1`, `isbn` is  `978-1234567890`, `genre` is `Programming`.
		* `B002` is `Data Structures` by `Jane Smith`, 400 pages, located in `A2`, `isbn` is  `978-0987654321`, `genre` is `Computer Science`.
	* 2 `Periodical`.
		* `P001` is `Byte` published by `McGraw-Hill, Inc.`, vol `6`, issue `8`, located in `P1`, `issn` is `0360-5280`, published `08/01/1981`.
		* `P002` is `Dr. Dobb's Journal` published by `M&T Publishing, Inc.`, vol `10`, issue `6`, located in `P1`, `issn` is `0278-6508`, published `06/01/1985`.
	* 1 `DVD`.
		* `D001` is `The Matrix`, directed by `Wachowski Sisters`, `136` minutes, rated `R`, located in `D1`,  `genre` is `Sci-Fi`.
	* 2 `LibraryMember`.
		* `M001` is `Alice Johnson`, `age` `25`, `email` `alice@email.com`, `phoneNumber` `555-1234`, `address` is `456 Oak St`, `Bookville`, `MD` `12347`.
		* `M002` is `Bob Wilson`, `age` `35`, `email` `bob@email.com`, `phoneNumber` `555-4321`, `address` is `654 Maple St`, `Media`, `PA` `12346`.

## The Game is Afoot

### Day 1: Encapsulation

#### Day 1: Tasks

* Create Base Classes with Proper Encapsulation (Afternoon)
* Create Test Class to Validate Base Classes (Evening)
	* Encapsulaton Test Class

#### Day 1: Deliverables

* Implement base classes with proper encapsulation
* Create a test class to demonstrate data hiding
* Write validation in setters to show controlled access

### Day 2: Abstraction and Inheritance

#### Day 2: Tasks

* Create Abstract Base Classes and Interfaces (Morning)
* Create Concrete Classes Using Inheritance (Afternoon)
* Create Test Class to Validate Inherited Classes (Evening)
  * Inheritance Test Class

#### Day 2: Deliverables

* Implement abstract class and interfaces
* Implement Book, DVD and Periodical classes
* Test inheritance relationships
* Test method overriding
* Design class hierarchy diagram

### Day 3: Polymorphism and System Integration

#### Day 3: Tasks

* Implement User Classes and Demonstrate Polymorphism (Morning)
* Create Main Library System
* Create Test Class to Validate Polymorphic Class Features

#### Day 3: Deliverables

* Implement member and librarian classes
* Implement polymorphic search and reporting
* Test polymorphic method calls
* Complete Main Library System

### Day 4: Data Integration

#### Day 4: Tasks

* Meet with Data Engineering to discuss the pipeline feed (Morning)
* Develop a plan to read in the data file(s) (Afternoon)

#### Day 4: Deliverables

* Create a specification for the data file(s)
* Create a plan to read in the data

### Day 5: Command Line Interface (CLI)

#### Day 5: Tasks

* Implement a CLI for the application
* Create Test Class to Validate CLI

#### Day 5: Deliverables

* Create a CLI
* Test CLI functionality

### Weekend: Merging Data with Application

#### Weekend: Tasks

* Obtain the pipeline data from Data Engineering
* Read in the data file(s)
* Beta Test the Library Management System

#### Weekend: Deliverables

* Complete the Library Management System
* System Test the Library Management System

## Climax and Denouement

* Create test cases that describe the `Library`, its `items`, `members` and `librarians`.
* Generate a late fee report.

## Planning & Execution of Design

ask yourselves:
* What are `Lists` and `ArrayLists`?
* How can they be used here?
