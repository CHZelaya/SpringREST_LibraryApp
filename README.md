# README for Library Management System - Assignment 1 & 2

## Objective
The purpose of this project is to create a Library Management System using Spring Boot and JPA. The application demonstrates the implementation of correct JPA relationships, including **One-to-One**, **One-to-Many**, and **Many-to-Many** associations. This assignment serves as a foundation for adding a security layer in future development (Assignment 2).

## Deliverables
1. **JPA Relationships and Constraints**:
    - Establish relationships and constraints between:
        - `LibraryMember` and `MembershipCard` (One-to-One)
        - `Book` and `Author` (Many-to-Many)
        - `LibraryMember` and `BorrowRecord` (One-to-Many)
        - `Book` and `BorrowRecord` (One-to-Many)
    - Ensure all constraints are respected (e.g., books cannot be deleted while borrowed).

2. **CRUD Operations**:
    - Develop full CRUD functionality for the following entities:
        - `LibraryMember`: Register, update, delete (only if no books are borrowed).
        - `MembershipCard`: Assign to members and delete when members are removed.
        - `Book`: Add, retrieve, update, delete (only if not currently borrowed).
        - `Author`: Create, update, delete (only if not linked to any books).
        - `BorrowRecord`: Create when a book is borrowed and update when returned.

3. **REST API Endpoints**:
    - Implement RESTful endpoints for all entities using appropriate HTTP methods (GET, POST, PUT, DELETE).

4. **Input Validation**:
    - Use Spring Boot validation annotations to ensure fields like `name`, `email`, `isbn`, `membershipDate`, and `publicationYear` are validated properly.

5. **Database Initialization**:
    - Load the database (`librarymanagement`) with seed data using `data.sql`, `import.sql`, or `CommandLineRunner`.

6. **Clean Code**:
    - Ensure the code is clean, well-structured, and includes comments.
    - Provide a detailed README for the repository.

## Entity Overview
### LibraryMember
- `id` (Primary Key, Auto-Generated)
- `name` (String, Not Null)
- `email` (String, Unique, Not Null)
- `membershipDate` (Date, Not Null)
- `membershipCard` (One-to-One with MembershipCard)
- `borrowedBooks` (One-to-Many with BorrowRecord)

### MembershipCard
- `id` (Primary Key, Auto-Generated)
- `cardNumber` (String, Unique, Not Null)
- `issueDate` (Date, Not Null)
- `expiryDate` (Date, Not Null)
- `libraryMember` (Bidirectional One-to-One with LibraryMember)

### Book
- `id` (Primary Key, Auto-Generated)
- `title` (String, Unique, Not Null)
- `isbn` (String, Unique, Not Null)
- `publicationYear` (Integer, Not Null)
- `authors` (Many-to-Many with Author)
- `borrowRecords` (One-to-Many with BorrowRecord)

### Author
- `id` (Primary Key, Auto-Generated)
- `name` (String, Not Null)
- `biography` (Text, Optional)
- `books` (Many-to-Many with Book)

### BorrowRecord
- `id` (Primary Key, Auto-Generated)
- `borrowDate` (Date, Not Null)
- `returnDate` (Date, Nullable)
- `libraryMember` (Many-to-One with LibraryMember)
- `book` (Many-to-One with Book)

## Database Details
- **Database Name**: `librarymanagement`
- **Username**: `oosdassignment`
- **Password**: `oosdassignment`

## Marking Rubric for Part 1
| Marking Component                                        | Points (Out of) |
|---------------------------------------------------------|------------------|
| All entities follow correct JPA annotations and relationships. | 7                |
| All APIs work correctly and follow proper HTTP methods.        | 7                |
| All constraints work as expected.                              | 7                |
| All input validation is implemented correctly.                 | 7                |
| Clean, well-structured code with comments and README.          | 7                |
| **Total**                                                    | **35**           |

## Submission
- Submit a zip folder of the application.
- Provide a link to the GitHub repository containing the project.

## Part 2: Security Layer Implementation
In the second phase of the project, a security layer will be added to the Library Management System. This will involve:

1. **User Authentication**:
    - Implement user authentication using Spring Security.
    - Allow users to register and log in to access the system.

2. **Role-Based Access Control**:
    - Define roles (e.g., ADMIN, MEMBER) and restrict access to certain endpoints based on user roles.

3. **Secure API Endpoints**:
    - Protect sensitive API endpoints to ensure that only authenticated users can access them.

4. **Password Management**:
    - Implement password hashing and secure storage practices to protect user credentials.

5. **Testing Security Features**:
    - Conduct thorough testing of the security features to ensure robustness against common vulnerabilities.

## Marking Rubric for Part 2
| Marking Component                                        | Points (Out of) |
|---------------------------------------------------------|------------------|
| User authentication is implemented correctly.           | 7                |
| Role-based access control is functioning as expected.   | 7                |
| API endpoints are secured properly.                      | 7                |
| Password management practices are implemented correctly. | 7                |
| Security features are thoroughly tested.                 | 7                |
| **Total**                                                | **35**           |